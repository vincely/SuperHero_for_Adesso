package com.vapps.superhero.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class HeroWithComics(
    @Embedded val heroEntity: HeroEntity,
    @Relation(
        parentColumn = "heroId",
        entity = ComicEntity::class,
        entityColumn = "comicName",
        associateBy = Junction(HeroComicCrossRef::class,
        parentColumn = "heroId",
        entityColumn = "comicName")
    )
    val comics: List<ComicEntity>
)

data class ComicWithHeroes(
    @Embedded val comic: ComicEntity,
    @Relation(
        parentColumn = "comicName",
        entity = HeroEntity::class,
        entityColumn = "heroId",
        associateBy = Junction(HeroComicCrossRef::class,
        parentColumn = "comicName",
        entityColumn = "heroId")
    )
    val heroes: List<HeroEntity>
)