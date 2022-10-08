package com.vapps.superhero.model

import androidx.room.Entity

@Entity(primaryKeys = ["heroId", "comicName"])
data class HeroComicCrossRef(
    val heroId: Int,
    val comicName: String
)