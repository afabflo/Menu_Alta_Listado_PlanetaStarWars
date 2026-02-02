package com.example.menu_alta_listadoplanetstarwars.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.menu_alta_listadoplanetstarwars.model.Planet

data class PersonWithPlanet(
    @Embedded val person : Person,
    //Se establece con la anotacion Relation sustituye al INNER JOIN
    //que tendriamos que hacer en la sentencia SELECT
    //Room hace una primera consulta y e inicializa el primer objeto
    //Despues hace una segunda consulta e inicializa el objeto planet
    @Relation(
        parentColumn = "planetId",
        entityColumn = "id"
    )
    val planet: Planet,

    )
