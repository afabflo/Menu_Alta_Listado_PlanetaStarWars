package com.example.menu_alta_listadoplanetstarwars.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.menu_alta_listadoplanetstarwars.model.Planet
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetDAO {

    @Insert
    suspend fun insert(planet: Planet)

    @Update
    suspend fun update(planet: Planet): Int

    @Delete
    suspend fun delete(planet: Planet)

    @Query("SELECT * FROM dbPlanet")
    fun getAllFlow(): Flow<List<Planet>>

    @Query("SELECT * FROM dbPlanet ORDER BY name ASC")
    fun getOrderByASC(): Flow<List<Planet>>

    @Query("SELECT * FROM dbPlanet ORDER BY name DESC")
    fun getOrderByDESC(): Flow<List<Planet>>

    @Query("SELECT * FROM dbPlanet WHERE name LIKE '%' || :search || '%' ORDER BY name ASC")
    fun searchPlanets(search: String): Flow<List<Planet>>

    @Query("SELECT * FROM dbPlanet WHERE LOWER(name) = LOWER(:name) LIMIT 1")
    suspend fun getPlanetByName(name: String): Planet?

    @Query("SELECT EXISTS(SELECT 1 FROM dbPlanet WHERE LOWER(name) = LOWER(:name))")
    suspend fun existsByName(name: String): Boolean
}