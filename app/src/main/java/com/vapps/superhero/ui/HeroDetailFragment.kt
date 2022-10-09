package com.vapps.superhero.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vapps.superhero.R
import com.vapps.superhero.databinding.FragmentHeroDetailBinding


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
        binding.detailTv.text = "clicked id is: $heroId"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}