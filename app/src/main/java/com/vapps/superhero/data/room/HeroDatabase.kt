package com.vapps.superhero.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vapps.superhero.model.Hero
import com.vapps.superhero.model.HeroComic

@Database(entities = [Hero::class, HeroComic::class], version = 1, exportSchema = false)
abstract class HeroDatabase : RoomDatabase() {
    abstract val heroDao: HeroDao

    companion object {
        @Volatile
        private var INSTANCE: HeroDatabase? = null

        fun getInstance(context: Context): HeroDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HeroDatabase::class.java,
                        "hero_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}