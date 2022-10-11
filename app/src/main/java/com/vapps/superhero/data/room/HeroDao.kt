package com.vapps.superhero.data.room

import androidx.lifecycle.LiveData
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
    @Query("SELECT * FROM heroentity ORDER BY name ASC")
    fun getHeroesWithComics(): LiveData<List<HeroWithComics>>

/*    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHeroComic(join: HeroComicCrossRef)*/

    @Transaction
    @Query("SELECT * FROM comicentity")
    fun getComicsWithHeroes(): LiveData<List<ComicWithHeroes>>

    @Query("SELECT * FROM heroentity WHERE heroId = :id")
    suspend fun getHero(id: Int): List<HeroEntity>

    @Query("SELECT (SELECT COUNT(*) FROM heroentity) == 0")
    suspend fun isEmpty(): Boolean
}