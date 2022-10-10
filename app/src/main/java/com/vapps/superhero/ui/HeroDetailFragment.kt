package com.vapps.superhero.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.vapps.superhero.R
import com.vapps.superhero.data.room.HeroDatabase
import com.vapps.superhero.databinding.FragmentHeroDetailBinding
import com.vapps.superhero.viewmodel.HeroDetailViewModel
import com.vapps.superhero.viewmodel.HeroViewModel
import com.vapps.superhero.viewmodel.HeroViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HeroDetailFragment : Fragment() {


    private var _binding: FragmentHeroDetailBinding? = null
    val binding: FragmentHeroDetailBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val heroId = HeroDetailFragmentArgs.fromBundle(requireArguments()).heroId

        val application = requireNotNull(this.activity).application
        val dao = HeroDatabase.getInstance(application).heroDao

        val factory = HeroViewModelFactory(dao)
        val viewModel = ViewModelProvider(requireActivity(), factory).get(HeroViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        val toolbar = binding.toolbar
        val navController = findNavController()
        toolbar.setupWithNavController(navController)

        viewModel.currentHero.value?.apply {
            toolbar.title = name
            binding.heroEntity = this
            if (description.isEmpty()){
                binding.detailTv.text = "No description"
            } else {
                binding.detailTv.text = description
            }
        }

        /*viewModel._currentHero.value?.imageLink?.let {
            val imgUri = it.toUri().buildUpon().scheme("https").build()
            binding.heroImage.load(imgUri) {
                placeholder(R.drawable.ic_launcher_foreground)
                error(com.google.android.material.R.drawable.mtrl_ic_error)
            }
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}