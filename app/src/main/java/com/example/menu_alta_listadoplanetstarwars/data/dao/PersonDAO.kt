package com.example.menu_alta_listadoplanetstarwars.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.menu_alta_listadoplanetstarwars.data.model.Person
import com.example.menu_alta_listadoplanetstarwars.data.model.PersonWithPlanet
import com.example.menu_alta_listadoplanetstarwars.model.Planet
import kotlinx.coroutines.flow.Flow
@Entity(
    foreignKeys =  [
        ForeignKey(
            entity = Planet::class,
            parentColumns = ["id"],
            childColumns = ["planetId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index(value = ["planetId"])]
)

@Dao
interface PersonDao {

    @Insert
    suspend fun insert(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Query("SELECT * FROM person")
    fun getAll(): Flow<List<Person>>

    @Query("SELECT EXISTS (SELECT * FROM person WHERE person.name = :name)")
    suspend fun exists(name: String): Boolean

    //Para contar cuantos personajes hay
    @Query("SELECT COUNT(*) FROM person")
    suspend fun count(): Int

    @Transaction
    @Query("SELECT * FROM person WHERE  id = :id")
    suspend fun  getPersonWithPlanet(id:Int): PersonWithPlanet
}