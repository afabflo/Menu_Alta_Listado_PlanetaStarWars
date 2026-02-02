package com.example.menu_alta_listadoplanetstarwars.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menu_alta_listadoplanetstarwars.data.repository.PlanetRepositorio
import com.example.menu_alta_listadoplanetstarwars.model.Planet
import com.example.menu_alta_listadoplanetstarwars.network.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditarViewModel @Inject constructor(
    private val repository: PlanetRepositorio
) : ViewModel() {

    // Variables de estado
    var name by mutableStateOf("")
    var rotationPeriod by mutableStateOf("")
    var orbitalPeriod by mutableStateOf("")
    var diameter by mutableStateOf("")
    var climate by mutableStateOf("")
    var gravity by mutableStateOf("")
    var terrain by mutableStateOf("")
    var surfaceWater by mutableStateOf("")
    var population by mutableStateOf("")

    // Variables internas para mantener datos que no se editan
    private var currentId: Int = 0
    private var currentResidents: List<String> = emptyList()
    private var currentFilms: List<String> = emptyList()

    init {
        val planeta = repository.planetaSeleccionado
        if (planeta != null) {
            currentId = planeta.id
            name = planeta.name
            rotationPeriod = planeta.rotation_period
            orbitalPeriod = planeta.orbital_period
            diameter = planeta.diameter
            climate = planeta.climate
            gravity = planeta.gravity
            terrain = planeta.terrain
            surfaceWater = planeta.surface_water
            population = planeta.population
            currentResidents = planeta.residents
            currentFilms = planeta.films
        }
    }
    fun actualizarPlaneta(onSuccess: () -> Unit) {
        viewModelScope.launch {
            // ... (creación del objeto planetaActualizado) ...

            val planetaActualizado = Planet(
                id = currentId, // ¡Muy importante! Usar el mismo ID para que sea UPDATE
                name = name,
                rotation_period = rotationPeriod,
                orbital_period = orbitalPeriod,
                diameter = diameter,
                climate = climate,
                gravity = gravity,
                terrain = terrain,
                surface_water = surfaceWater,
                population = population,
                residents = currentResidents, // Mantenemos los originales
                films = currentFilms          // Mantenemos los originales
            )
            val result = repository.update(planetaActualizado)

            if (result is BaseResult.Sucess) {
                onSuccess()
            } else {
                println("ERROR AL ACTUALIZAR: El repositorio devolvió Error. ID: $currentId")
            }
        }
    }


}