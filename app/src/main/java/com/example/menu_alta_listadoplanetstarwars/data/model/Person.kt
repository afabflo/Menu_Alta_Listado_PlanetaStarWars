package com.example.menu_alta_listadoplanetstarwars.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.autofill.Autofill
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import javax.annotation.Nonnull

@Entity
@Parcelize
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @NonNull
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    val imgStarWars: Int,
    val planetId: Int
): Parcelable