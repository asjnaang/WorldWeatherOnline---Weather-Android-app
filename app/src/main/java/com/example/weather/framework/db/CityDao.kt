package com.example.weather.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface CityDao {
    @Insert(onConflict = REPLACE)
    suspend fun addCity(city: CityEntity)

    @Query("SELECT * FROM city")
    suspend fun getCities(): List<CityEntity>

    @Query("SELECT * FROM city where id = :id")
    suspend fun getCity(id: Int): CityEntity
}