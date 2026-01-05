package com.example.menu_alta_listadoplanetstarwars.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.menu_alta_listadoplanetstarwars.data.repository.PlanetRepositorio
import com.example.menu_alta_listadoplanetstarwars.network.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditarViewModel @Inject constructor(private val repositorio: PlanetRepositorio) : ViewModel() {
    var name by mutableStateOf("")
    var rotationPeriod by mutableStateOf("")
    var orbitalPeriod by mutableStateOf("")
    var diameter by mutableStateOf("")
    var climate by mutableStateOf("")
    var gravity by mutableStateOf("")
    var terrain by mutableStateOf("")
    var surfaceWater by mutableStateOf("")
    var population by mutableStateOf("")
    init{
        repositorio.planetaSeleccionado?.let { planet ->
            name = planet.name
            rotationPeriod = planet.rotation_period
            orbitalPeriod = planet.orbital_period
            diameter = planet.diameter
            climate = planet.climate
            gravity = planet.gravity
            terrain = planet.terrain
            surfaceWater = planet.surface_water
            population = planet.population
        }

    }
    fun actualizarPlaneta(onSucces:() -> Unit) {
        repositorio.planetaSeleccionado?.let { original ->
            val planetaEditado = original.copy(
                name = name,
                rotation_period = rotationPeriod,
                orbital_period = orbitalPeriod,
                diameter = diameter,
                climate = climate,
                gravity = gravity,
                terrain = terrain,
                surface_water = surfaceWater,
                population = population
            )
            val resultado = repositorio.update(planetaEditado)
            if (resultado is BaseResult.Sucess) {
                onSucces()
            }
        }
    }
}