package com.vapps.superhero.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vapps.superhero.data.room.HeroDao
import com.vapps.superhero.model.Hero
import com.vapps.superhero.model.HeroEntity
import com.vapps.superhero.model.HeroWithComics
import com.vapps.superhero.network.HeroApi
import kotlinx.coroutines.launch

class HeroViewModel(private val dao: HeroDao) : ViewModel() {


    var _currentHero = MutableLiveData<HeroEntity>()
    val currentHero: LiveData<HeroEntity> get() = _currentHero

    private val _navigateToDetail = MutableLiveData<Int?>()
    val navigateToDetail: LiveData<Int?> get() = _navigateToDetail

    private val _apiStatus = MutableLiveData<String>()
    val apiStatus: LiveData<String> get() = _apiStatus

    var counter = 0

    val _heroes = MutableLiveData<List<Hero>>()
    val heroes: LiveData<List<Hero>> get() = _heroes

    var allHeroes = dao.getHeroesWithComics()

    init {
        getHeroes()
    }



    private fun getHeroes() {
        viewModelScope.launch {
            if (dao.isEmpty()) {
                _heroes.value = HeroApi.retrofitService.getAllHeroes().data.results.map {  it.toHero()}
                fillDatabase()
            }
        }
    }

    fun fillDatabase() {
        var heroList: List<Hero> = listOf()
        heroes.value?.let {
            heroList = it
        }
        viewModelScope.launch {
            for (hero in heroList) {
                hero.apply {
                    dao.insertHero(HeroEntity(heroId,name,description,imageLink))
                }
            }
        }
    }

    fun setCurrentHero(id: Int){
        var heroList: List<HeroWithComics> = listOf()
        allHeroes.value?.let {
            heroList = it
        }
        for (hero in heroList) {
            if (hero.heroEntity.heroId == id) {
                _currentHero.value = hero.heroEntity
            }
        }
    }

    fun onHeroClicked(id: Int) {
        _navigateToDetail.value = id
    }

    fun onDetailNavigated() {
        _navigateToDetail.value = null
    }
}