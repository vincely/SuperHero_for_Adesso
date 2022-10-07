package com.vapps.superhero.model

data class Hero(
    val id : Int,
    val name : String,
    val description : String,
    val thumbnail : String,
    val thumbnailExt : String,
    val comics : List<String>
)