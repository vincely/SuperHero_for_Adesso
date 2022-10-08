package com.vapps.superhero.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HeroComic (
    @PrimaryKey var comicName: String,
    var heroId: Int
)