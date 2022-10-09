package com.vapps.superhero.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["heroId", "comicName"])
data class HeroComicCrossRef(
    val heroId: Int,
    @ColumnInfo(index = true)
    val comicName: String
)