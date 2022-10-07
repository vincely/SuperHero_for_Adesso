package com.vapps.superhero.model

data class Hero(
    val id : Int,
    val name : String,
    val description : String,
    val imageLink : String,
    val comics : List<String>
)