package com.vapps.superhero.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.picasso.Picasso
import com.vapps.superhero.data.room.HeroDao
import com.vapps.superhero.model.*
import com.vapps.superhero.network.HeroApi
import kotlinx.coroutines.launch
import java.lang.Exception

class HeroViewModel(private val dao: HeroDao) : ViewModel() {


    var _bgColor = MutableLiveData<String>()
    val bgColor: LiveData<String> get() = _bgColor

    var _currentHero = MutableLiveData<HeroEntity>()
    val currentHero: LiveData<HeroEntity> get() = _currentHero

    var _currentHeroComics = MutableLiveData<List<String>>()
    val currentHeroComics: LiveData<List<String>> get() = _currentHeroComics

    private val _navigateToDetail = MutableLiveData<Int?>()
    val navigateToDetail: LiveData<Int?> get() = _navigateToDetail

    val _heroes = MutableLiveData<List<Hero>>()
    val heroes: LiveData<List<Hero>> get() = _heroes

    var allHeroes = dao.getHeroesWithComics()
    var allComics = dao.getComicsWithHeroes()

    init {
        getHeroes()
    }



    private fun getHeroes() {
        viewModelScope.launch {
            if (dao.isEmpty()) {
                var offset = 0
                val numberOfHeroes = HeroApi.retrofitService.getAllHeroes(offset = offset.toString()).data.total
                val numberOfCalls = (numberOfHeroes / 100)

                for (i in 0..numberOfCalls){
                    val result = HeroApi.retrofitService.getAllHeroes(offset = offset.toString()).data.results.map {  it.toHero()}
                    _heroes.value = result
                    fillDatabase(result)
                    offset+=100
                }
            }
        }
    }

    fun fillDatabase(heroList: List<Hero>) {

        viewModelScope.launch {
            for (hero in heroList) {
                if (!(hero.imageLink.equals( "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg")) && hero.description.isNotEmpty()){
                    hero.apply {
                        dao.insertHero(HeroEntity(heroId,name,description,imageLink))
                        for (comicName in comics) {
                            dao.insertComic(ComicEntity(comicName))
                        }
                    }
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
                Log.d("HeroWithComics", "${hero.comics}")
                _currentHeroComics.value = hero.comics.map { it.comicName }
            }
        }
    }

    fun getBgColorFromBitmap(bitmap: Bitmap): Int {
        return Bitmap.createScaledBitmap(bitmap, 1, 1, false).getPixel(1, 1)
    }

    fun urlToBitmap() : Bitmap? {
        var bmp : Bitmap ? = null
        Picasso.get().load(currentHero.value?.imageLink).into(object : com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                bmp =  bitmap
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
        })
        return bmp
    }

    fun setBgColor() {
        val bitmap = urlToBitmap()
        _bgColor.value = getBgColorFromBitmap(bitmap!!).toString()
        //_bgColor.value = color.toString()
    }


    fun onHeroClicked(id: Int) {
        _navigateToDetail.value = id
    }

    fun onDetailNavigated() {
        _navigateToDetail.value = null
    }
}