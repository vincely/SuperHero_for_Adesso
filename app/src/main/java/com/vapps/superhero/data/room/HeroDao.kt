package com.vapps.superhero.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vapps.superhero.model.*

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(heroEntity: HeroEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComic(comicEntity: ComicEntity)

    @Transaction
    @Query("SELECT * FROM heroentity")
    suspend fun getHeroesWithComics(): List<HeroWithComics>

    @Transaction
    @Query("SELECT * FROM comicentity")
    suspend fun getComicsWithHeroes(): List<ComicWithHeroes>
}