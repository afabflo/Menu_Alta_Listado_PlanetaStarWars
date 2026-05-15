package com.example.menu_alta_listadoplanetstarwars.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menu_alta_listadoplanetstarwars.data.dao.FilmDAO
import com.example.menu_alta_listadoplanetstarwars.data.dao.PersonDao
import com.example.menu_alta_listadoplanetstarwars.data.dao.PlanetDAO
import com.example.menu_alta_listadoplanetstarwars.data.model.Film
import com.example.menu_alta_listadoplanetstarwars.data.model.Person
import com.example.menu_alta_listadoplanetstarwars.data.repository.PlanetRepositorio
import com.example.menu_alta_listadoplanetstarwars.model.Planet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListadoViewModel @Inject constructor(
    private val planetRepositorio: PlanetRepositorio,
    private val planetDAO: PlanetDAO,
    private val personDao: PersonDao,
    private val filmDAO: FilmDAO
) : ViewModel() {

    var planetSearch by mutableStateOf("")
    var personSearch by mutableStateOf("")

    val planetas: Flow<List<Planet>>
        get() = if (planetSearch.isBlank()) {
            planetRepositorio.getDataFlow()
        } else {
            planetRepositorio.searchPlanets(planetSearch)
        }

    fun selecionarPlaneta(planet: Planet) {
        planetRepositorio.planetaSeleccionado = planet
    }

    fun borrarPlaneta(planet: Planet) {
        viewModelScope.launch {
            planetRepositorio.delete(planet)
        }
    }

    val personajes: Flow<List<Person>>
        get() = if (personSearch.isBlank()) {
            personDao.getAll()
        } else {
            personDao.searchPeople(personSearch)
        }

    fun borrarPersonaje(person: Person) {
        viewModelScope.launch {
            personDao.delete(person)
        }
    }

    val peliculas: Flow<List<Film>> = filmDAO.getAll()

    fun borrarPelicula(film: Film) {
        viewModelScope.launch {
            filmDAO.delete(film)
        }
    }
}