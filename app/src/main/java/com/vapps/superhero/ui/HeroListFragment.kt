package com.vapps.superhero.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vapps.superhero.R
import com.vapps.superhero.data.room.HeroDatabase
import com.vapps.superhero.databinding.FragmentHeroListBinding
import com.vapps.superhero.ui.adapter.HeroListAdapter
import com.vapps.superhero.viewmodel.HeroViewModel
import com.vapps.superhero.viewmodel.HeroViewModelFactory

/* Home Fragment that shows a gridview of all marvel heroes
* */
class HeroListFragment : Fragment() {

    lateinit var heroViewModel: HeroViewModel
    private var _binding: FragmentHeroListBinding? = null
    val binding: FragmentHeroListBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHeroListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dao = HeroDatabase.getInstance(application).heroDao
        val factory = HeroViewModelFactory(dao)
        heroViewModel = ViewModelProvider(this, factory).get(HeroViewModel::class.java)

        binding.apply {
            viewModel = heroViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        val heroListAdapter = HeroListAdapter{
                heroId -> heroViewModel.onHeroClicked(heroId)
        }
        binding.heroList.adapter = heroListAdapter

        heroViewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            heroId -> heroId?.let {
                val action = HeroListFragmentDirections.actionHeroListFragmentToHeroDetailFragment(heroId)
            this.findNavController().navigate(action)
            heroViewModel.onDetailNavigated()
        }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}