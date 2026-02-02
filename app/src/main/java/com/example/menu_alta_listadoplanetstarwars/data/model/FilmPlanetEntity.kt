package com.example.menu_alta_listadoplanetstarwars.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.menu_alta_listadoplanetstarwars.model.Planet

@Entity(
    tableName = "film_planet",
    primaryKeys = ["filmId", "planetId"],
    foreignKeys = [
        ForeignKey(
            entity = Film::class,
            parentColumns = ["filmId"],
            childColumns = ["filmId"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = Planet::class,
            parentColumns = ["id"],
            childColumns = ["planetId"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class FilmPlanetEntity(
    val filmId: Int,
    val planetId: Int
)