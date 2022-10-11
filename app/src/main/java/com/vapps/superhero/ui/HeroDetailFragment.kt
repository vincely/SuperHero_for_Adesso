package com.vapps.superhero.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.vapps.superhero.R
import com.vapps.superhero.data.room.HeroDatabase
import com.vapps.superhero.databinding.FragmentHeroDetailBinding
import com.vapps.superhero.ui.adapter.ComicListAdapter
import com.vapps.superhero.viewmodel.HeroViewModel
import com.vapps.superhero.viewmodel.HeroViewModelFactory


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
        val viewModel = ViewModelProvider(requireActivity(), factory)[HeroViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        val toolbar = binding.toolbar
        val navController = findNavController()
        toolbar.setupWithNavController(navController)


        viewModel.currentHero.value?.apply {
            toolbar.title = name
            binding.heroEntity = this
        }

        viewModel.currentHeroComics.value?.let {
            val adapter = ComicListAdapter(it)
            binding.comicList.adapter = adapter
        }

        binding.sendHero.setOnClickListener {
            val message = resources.getString(R.string.send_hero_text, viewModel.currentHero.value?.name.toString(), viewModel.currentHeroComics.value?.size.toString())
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "No activity can handle this intent", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}