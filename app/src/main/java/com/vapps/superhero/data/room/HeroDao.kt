package com.vapps.superhero.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vapps.superhero.model.ComicWithHeroes
import com.vapps.superhero.model.Hero
import com.vapps.superhero.model.HeroComic
import com.vapps.superhero.model.HeroWithComics

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(hero: Hero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComic(heroComic: HeroComic)

    @Transaction
    @Query("SELECT * FROM hero")
    suspend fun getHeroesWithComics(): List<HeroWithComics>

    @Transaction
    @Query("SELECT * FROM hero")
    suspend fun getComicsWithHeroes(): List<ComicWithHeroes>
}