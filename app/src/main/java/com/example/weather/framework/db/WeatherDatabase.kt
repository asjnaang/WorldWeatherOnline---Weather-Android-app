package com.example.weather.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "weather.db"
        private var instance: WeatherDatabase? = null
        private fun create(context: Context): WeatherDatabase =
            Room.databaseBuilder(context, WeatherDatabase::class.java, DATABASE_NAME)
                .build()
        fun getInstance(context: Context): WeatherDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun cityDao(): CityDao
}