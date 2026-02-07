package com.example.menu_alta_listadoplanetstarwars.data.dao
import com.example.menu_alta_listadoplanetstarwars.model.Planet
import android.icu.text.MessagePattern
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetDAO {
    @Insert
    suspend  fun insert(planet: Planet)


    @Delete
   suspend fun delete(planet: Planet)

    @Query("SELECT * FROM dbPlanet WHERE name = :name LIMIT 1")
    suspend fun getPlanetByName(name: String): Planet?
    @Query("SELECT * FROM dbPlanet")
    fun getAllFlow(): Flow<List<Planet>>


    @Update
    suspend fun update(planet: Planet): Int // Devuelve el nº de filas afectadas

   @Query("SELECT EXISTS(SELECT * FROM dbPlanet WHERE id = :planetId)")
    fun exists(planetId: Int): Boolean

}