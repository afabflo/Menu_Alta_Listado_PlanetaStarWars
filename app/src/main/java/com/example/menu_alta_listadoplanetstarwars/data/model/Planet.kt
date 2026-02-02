package com.example.menu_alta_listadoplanetstarwars.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import javax.annotation.Nonnull

@Parcelize
@Entity (tableName = "dbPlanet")
data class Planet(
    @PrimaryKey (autoGenerate = true)//Siempre va arriba de la que sea pk
    val id: Int = 0,
    @NonNull
    var name: String,
    var rotation_period: String,
    var orbital_period: String,
    var diameter: String,
    var climate: String,
    var gravity: String,
    var terrain: String,
    var surface_water: String,
    var population: String,
    var residents: List<String> = emptyList(),
    var films: List<String> = emptyList()
): Parcelable
