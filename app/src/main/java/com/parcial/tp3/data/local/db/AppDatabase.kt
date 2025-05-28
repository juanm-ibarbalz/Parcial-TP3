package com.parcial.tp3.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.parcial.tp3.data.local.dao.FavouriteDao
import com.parcial.tp3.data.local.entity.FavouriteEntity

@Database(
    entities = [FavouriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao
}