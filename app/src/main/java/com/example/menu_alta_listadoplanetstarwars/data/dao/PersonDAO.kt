package com.example.menu_alta_listadoplanetstarwars.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.menu_alta_listadoplanetstarwars.data.model.Person
import com.example.menu_alta_listadoplanetstarwars.data.model.PersonWithPlanet
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: Person)

    @Update
    suspend fun update(person: Person): Int

    @Delete
    suspend fun delete(person: Person)

    @Query("SELECT * FROM Person")
    fun getAll(): Flow<List<Person>>

    @Query("SELECT * FROM Person WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Person?

    @Query("SELECT * FROM Person WHERE name LIKE '%' || :search || '%' ORDER BY name ASC")
    fun searchPeople(search: String): Flow<List<Person>>

    @Query("SELECT EXISTS(SELECT 1 FROM Person WHERE LOWER(name) = LOWER(:name))")
    suspend fun exists(name: String): Boolean

    @Query("SELECT COUNT(*) FROM Person")
    suspend fun count(): Int

    @Transaction
    @Query("SELECT * FROM Person WHERE id = :id LIMIT 1")
    suspend fun getPersonWithPlanet(id: Int): PersonWithPlanet
}