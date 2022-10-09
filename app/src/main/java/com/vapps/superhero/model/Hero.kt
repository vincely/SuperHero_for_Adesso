package com.vapps.superhero.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

data class Hero(
    val heroId : Int,
    val name : String,
    val description : String,
    val imageLink : String,
    val comics : List<String>
)

