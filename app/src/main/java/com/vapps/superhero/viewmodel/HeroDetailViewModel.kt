package com.vapps.superhero.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vapps.superhero.data.room.HeroDao
import com.vapps.superhero.model.HeroEntity
import kotlinx.coroutines.launch

class HeroDetailViewModel(val dao: HeroDao, var heroId: Int) : ViewModel() {


    val allHeroes = dao.getHeroesWithComics()

    var _hero = MutableLiveData<HeroEntity>()
    val hero: LiveData<HeroEntity> get() = _hero


    class HeroDetailViewModelFactory(private val dao: HeroDao, private var heroId: Int): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HeroDetailViewModel::class.java)) {
                return HeroDetailViewModel(dao, heroId) as T
            }
            throw IllegalArgumentException("Unknown Viewmodel")
        }
    }
}