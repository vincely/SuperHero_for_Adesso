package com.vapps.superhero.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vapps.superhero.R
import com.vapps.superhero.databinding.FragmentHeroListBinding
import com.vapps.superhero.ui.adapter.HeroListAdapter
import com.vapps.superhero.viewmodel.HeroViewModel

/* Home Fragment that shows a gridview of all marvel heroes
* */
class HeroListFragment : Fragment() {

    private val sharedViewModel: HeroViewModel by viewModels()
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

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            heroList.adapter = HeroListAdapter()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}