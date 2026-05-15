package com.example.menu_alta_listadoplanetstarwars.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.menu_alta_listadoplanetstarwars.data.model.Film
import com.example.menu_alta_listadoplanetstarwars.data.model.FilmPlanetEntity
import com.example.menu_alta_listadoplanetstarwars.data.model.FilmWithPlanet
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDAO {

    @Insert
    suspend fun insert(film: Film)

    @Update
    suspend fun update(film: Film)

    @Delete
    suspend fun delete(film: Film)

    @Query("SELECT * FROM film")
    fun getAll(): Flow<List<Film>>

    @Query("SELECT * FROM film ORDER BY title DESC")
    fun orderByNameDesc(): Flow<List<Film>>

    @Query("SELECT EXISTS(SELECT 1 FROM film WHERE filmId = :id)")
    suspend fun exists(id: Int): Boolean

    @Query("SELECT EXISTS(SELECT 1 FROM film WHERE LOWER(title) = LOWER(:title) AND releaseDate = :releaseDate)")
    suspend fun existsByTitleAndDate(title: String, releaseDate: String): Boolean

    @Transaction
    @Query("SELECT * FROM film WHERE filmId = :id")
    suspend fun getFilmWithPlanet(id: Int): FilmWithPlanet

    @Insert
    suspend fun insertJoinDilmPlanet(join: FilmPlanetEntity)

    @Query("SELECT * FROM film WHERE filmId = :id LIMIT 1")
    suspend fun getFilmById(id: Int): Film?
}