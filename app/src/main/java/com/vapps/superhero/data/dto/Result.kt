package com.vapps.superhero.data.dto

import com.vapps.superhero.model.Hero

data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
){
    fun toHero(): Hero{
        return Hero(
            id = id,
            name = name,
            description = description,
            imageLink = thumbnail.path+ "." +thumbnail.extension,
            comics = comics.items.map {
                it.name
            }


        )
    }
}