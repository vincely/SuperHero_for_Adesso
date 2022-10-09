package com.vapps.superhero.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class HeroEntity(
    @PrimaryKey val heroId : Int,
    val name : String,
    val description : String,
    val imageLink : String
)

@Entity
data class ComicEntity (
    @PrimaryKey
    var comicName: String,
    var heroId: Int
)