package com.example.menu_alta_listadoplanetstarwars.data

import com.example.menu_alta_listadoplanetstarwars.model.Planet
import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.menu_alta_listadoplanetstarwars.data.dao.FilmDAO
import com.example.menu_alta_listadoplanetstarwars.data.dao.PersonDao
import com.example.menu_alta_listadoplanetstarwars.data.dao.PlanetDAO
import com.example.menu_alta_listadoplanetstarwars.data.model.Film
import com.example.menu_alta_listadoplanetstarwars.data.model.FilmPlanetEntity
import com.example.menu_alta_listadoplanetstarwars.data.model.Person
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

/*

CORREGIR VER SI ES CORRECTO LO DE DATABASE PARAMETROPOS Y INSERTAR BORRAR INIT NOTIFY Y COSAS INUTILES DE
REPOSITORIO Y VER EL UPDATE
 */

@Database(
    entities = [Planet::class, Person::class, Film::class, FilmPlanetEntity::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class StarWarsDatabase : RoomDatabase() {

    abstract fun planetDao(): PlanetDAO
    abstract fun personDao(): PersonDao // Cambiado de PersonDao() a personDao()
    abstract fun filmDao(): FilmDAO

    companion object {
        @Volatile
        private var INSTANCE: StarWarsDatabase? = null

        fun getDatabase(context: Context): StarWarsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StarWarsDatabase::class.java,
                    "star_wars_db_v5"
                )
                    .fallbackToDestructiveMigration() //EXAMEN
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Executors.newSingleThreadExecutor().execute {
                                INSTANCE?.let { database ->
                                    prepopulateDatabase(database)
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        @SuppressLint("SuspiciousIndentation")
        private fun prepopulateDatabase(database: StarWarsDatabase) {
            val planetDao = database.planetDao()
            val filmDao = database.filmDao()
            val personDao = database.personDao()

            runBlocking {
                planetDao.insert(
                    Planet(
                        id = 1,
                        name = "Tatooine",
                        rotation_period = "23",
                        orbital_period = "304",
                        diameter = "10465",
                        climate = "arid",
                        gravity = "1 standard",
                        terrain = "desert",
                        surface_water = "1",
                        population = "200000",
                        residents = listOf("Luke Skywalker", "Anakin Skywalker", "Shmi Skywalker"),
                        films = listOf("A New Hope", "The Phantom Menace")
                    )
                )
                planetDao.insert(
                    Planet(
                        id = 3,
                        name = "Yavin IV",
                        rotation_period = "24",
                        orbital_period = "4818",
                        diameter = "10200",
                        climate = "temperate, tropical",
                        gravity = "1 standard",
                        terrain = "jungle, rainforests",
                        surface_water = "8",
                        population = "1000",
                        residents = emptyList(),
                        films = listOf("A New Hope")
                    )
                )
                planetDao.insert(
                    Planet(
                        id = 2,
                        name = "Alderaan",
                        rotation_period = "24",
                        orbital_period = "364",
                        diameter = "12500",
                        climate = "temperate",
                        gravity = "1 standard",
                        terrain = "grasslands, mountains",
                        surface_water = "40",
                        population = "2000000000",
                        residents = listOf("Leia Organa"),
                        films = listOf("A New Hope")
                    )
                )
                planetDao.insert(
                    Planet(
                        id = 6,
                        name = "Bespin",
                        rotation_period = "12",
                        orbital_period = "5110",
                        diameter = "118000",
                        climate = "temperate",
                        gravity = "1.5",
                        terrain = "gas giant",
                        surface_water = "0",
                        population = "6000000",
                        residents = listOf("Lando Calrissian"),
                        films = listOf("The Empire Strikes Back")
                    )
                )

                personDao.insert(
                    Person(
                        name = "Luke Skywalker",
                        height = "172",
                        mass = "77",
                        hairColor = "blond",
                        skinColor = "fair",
                        eyeColor = "blue",
                        birthYear = "19BBY",
                        gender = "male",
                        imgStarWars = 0,
                        planetId = 1
                    )
                )
                personDao.insert(
                    Person(
                        name = "Leia Organa",
                        height = "150",
                        mass = "49",
                        hairColor = "brown",
                        skinColor = "light",
                        eyeColor = "brown",
                        birthYear = "19BBY",
                        gender = "female",
                        imgStarWars = 0,
                        planetId = 2
                    )
                )

                filmDao.insert(
                    Film(
                        filmId = 1,
                        title = "La Amenaza Fantasma",
                        episode = "1",
                        director = "George Lucas",
                        releaseDate = "19-05-1999",
                        era = "Prequel",
                        rating = "PG",
                        openingText = "Hace mucho tiempo...",
                        isOriginalTrilogy = false
                    )
                )

                filmDao.insertJoinDilmPlanet(FilmPlanetEntity(filmId = 1, planetId = 1))
                filmDao.insertJoinDilmPlanet(FilmPlanetEntity(filmId = 1, planetId = 2))

                val resultFilm = filmDao.getFilmWithPlanet(1)
                println("Pelicula ${resultFilm.film.title}")
                println("Planetas asociados: ${resultFilm.planet.map { it.name }}")
                val peliculaConPlanetas = filmDao.getFilmWithPlanet(1)
                println(" La película '${peliculaConPlanetas.film.title}' ahora tiene estos planetas: ${peliculaConPlanetas.planet.map { it.name }}")
                val resultPersonWithPlanet = personDao.getPersonWithPlanet(1)
                println("${resultPersonWithPlanet.person.name} nacio en el ${resultPersonWithPlanet.planet.name}")
            }
        }
    }
}