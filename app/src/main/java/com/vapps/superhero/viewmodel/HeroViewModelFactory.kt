package com.vapps.superhero.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vapps.superhero.data.room.HeroDao

class HeroViewModelFactory(private val dao: HeroDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroViewModel::class.java)) {
            return HeroViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel")
    }
}