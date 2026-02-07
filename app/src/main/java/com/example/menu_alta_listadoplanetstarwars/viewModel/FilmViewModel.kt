package com.example.menu_alta_listadoplanetstarwars.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menu_alta_listadoplanetstarwars.data.dao.FilmDAO
import com.example.menu_alta_listadoplanetstarwars.data.dao.PersonDao
import com.example.menu_alta_listadoplanetstarwars.data.model.Film
import com.example.menu_alta_listadoplanetstarwars.data.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val filmDao: FilmDAO // Inyectamos el DAO de pelis
) : ViewModel() {

    // Lista de pelis (la podemos sacar ordenada por nombre si quieres cumplir la Actividad 1)
    val films = filmDao.orderByNameDesc()

    fun borrarPelicula(film: Film) {
        viewModelScope.launch(Dispatchers.IO) {
            filmDao.delete(film)
        }
    }

    // Función para ver si una peli ya existe (por si haces un formulario de alta de pelis)
    suspend fun existePeli(title: String, date: String): Boolean {
        return withContext(Dispatchers.IO) {
            filmDao.existsByTitleAndDate(title, date)
        }
    }
}