package com.example.menu_alta_listadoplanetstarwars.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.menu_alta_listadoplanetstarwars.model.Planet

data class FilmWithPlanet(
    @Embedded val film: Film,
    @Relation(
        parentColumn = "filmId",    // ID en la tabla Film
        entityColumn = "id",        // ID en la tabla Planet
        associateBy = Junction(
            value = FilmPlanetEntity::class,
            parentColumn = "filmId",
            entityColumn = "planetId"
        )
    )
    val planet: List<Planet>
)
/*
data class FilmWithPlanet(
    @Embedded val film: Film,
    @Relation(
        parentColumn = "filmId",
        entityColumn = "id"
        , associateBy = Junction(FilmPlanetEntity::class)
    )
val planet: List<Planet>
)
*/