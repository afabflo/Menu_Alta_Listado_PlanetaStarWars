package com.example.menu_alta_listadoplanetstarwars.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menu_alta_listadoplanetstarwars.data.dao.FilmDAO
import com.example.menu_alta_listadoplanetstarwars.data.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val filmDao: FilmDAO
) : ViewModel() {

    val films = filmDao.orderByNameDesc()

    fun borrarPelicula(film: Film) {
        viewModelScope.launch(Dispatchers.IO) {
            filmDao.delete(film)
        }
    }
}