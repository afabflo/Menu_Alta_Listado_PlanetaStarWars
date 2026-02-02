package com.example.menu_alta_listadoplanetstarwars.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Data class de pelicula con todos sus campos
 *
 * @param filmId es el id de la pelicula
 * @param title es el titulo de la peli
 * @param episode es el episidio al que pertenece la peli
 * @param director es el director de la peli
 * @param releaseDate es la fecha de lanzamiento de la peli
 * @param era es la era de la peli
 * @param rating es la clasificacion de la peli
 * @param openingText es el texto de apertuara
 * @param isOriginalTrilogy es un booleano de si es original o no la trilogia
 */
@Entity
@Parcelize
data class Film(
    @PrimaryKey
    val filmId: Int,
    @NonNull
    val title: String,
    val episode: String,
    val director: String,
    val releaseDate: String,
    val era: String,
    val rating: String,
    val openingText: String,
    val isOriginalTrilogy: Boolean
): Parcelable