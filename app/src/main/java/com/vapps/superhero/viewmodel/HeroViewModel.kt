package com.vapps.superhero.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vapps.superhero.model.Hero
import com.vapps.superhero.network.HeroApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HeroViewModel : ViewModel() {

    private val _apiStatus = MutableLiveData<String>()
    val apiStatus: LiveData<String> get() = _apiStatus

    var counter = 0

    val _heroes = MutableLiveData<List<Hero>>()
    val heroes: LiveData<List<Hero>> get() = _heroes

    init {
        getHeroes()
    }

    private fun getHeroes() {
        viewModelScope.launch {
                _heroes.value = HeroApi.retrofitService.getAllHeroes().data.results.map {  it.toHero()}


        }
    }
}