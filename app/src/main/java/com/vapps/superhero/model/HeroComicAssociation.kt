package com.vapps.superhero.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class HeroWithComics(
    @Embedded val heroEntity: HeroEntity,
    @Relation(
        parentColumn = "heroId",
        entityColumn = "comicName",
        associateBy = Junction(HeroComicCrossRef::class)
    )
    val comics: List<ComicEntity>
)

data class ComicWithHeroes(
    @Embedded val comic: ComicEntity,
    @Relation(
        parentColumn = "comicName",
        entityColumn = "heroId",
        associateBy = Junction(HeroComicCrossRef::class)
    )
    val heroes: List<HeroEntity>
)