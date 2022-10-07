package com.vapps.superhero.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vapps.superhero.network.HeroApi
import kotlinx.coroutines.launch

class HeroViewModel : ViewModel() {

    private val _apiStatus = MutableLiveData<String>()
    val apiStatus: LiveData<String> get() = _apiStatus


    fun getHeroes() {
        viewModelScope.launch {
            try {
                _apiStatus.value = HeroApi.retrofitService.getAllHeroes().data.results[0].name
            } catch (e: Exception) {
                _apiStatus.value = e.message
            }
        }
    }
}