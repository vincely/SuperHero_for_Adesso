package com.vapps.superhero.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Hero(
    @PrimaryKey val heroId : Int,
    val name : String,
    val description : String,
    val imageLink : String,
    @Ignore val comics : List<String>
) {
    // Apparently you cannot use @Ignore with kapt, because room wants a matching constructor.
    // This is why a secondary constructor had to be declared
    constructor(heroId: Int, name: String, description: String, imageLink: String) : this(heroId, name, description, imageLink, listOf())
}

