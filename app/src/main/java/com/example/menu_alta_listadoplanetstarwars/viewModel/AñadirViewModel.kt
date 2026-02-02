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
class AñadirViewModel @Inject constructor(
    private val repositorio: PlanetRepositorio
) : ViewModel() {

    // Estados del formulario
    var name by mutableStateOf("")
    var rotationPeriod by mutableStateOf("")
    var orbitalPeriod by mutableStateOf("")
    var diameter by mutableStateOf("")
    var climate by mutableStateOf("")
    var gravity by mutableStateOf("")
    var terrain by mutableStateOf("")
    var surfaceWater by mutableStateOf("")
    var population by mutableStateOf("")

    var errorMessage by mutableStateOf<String?>(null)

    fun resetear() {
        name = ""; rotationPeriod = ""; orbitalPeriod = ""; diameter = ""
        climate = ""; gravity = ""; terrain = ""; surfaceWater = ""; population = ""
        errorMessage = null
    }


    fun insertarPlaneta(onSuccess: () -> Unit) {
        if (name.isBlank()) {
            errorMessage = "El nombre es obligatorio"
            return
        }

        viewModelScope.launch {
            val planetaNuevo = Planet(
                id = 0,
                name = name,
                rotation_period = rotationPeriod,
                orbital_period = orbitalPeriod,
                diameter = diameter,
                climate = climate,
                gravity = gravity,
                terrain = terrain,
                surface_water = surfaceWater,
                population = population,
                residents = emptyList(),
                films = emptyList()
            )

            val result = repositorio.add(planetaNuevo)

            when (result) {
                is BaseResult.Sucess -> {
                    resetear()
                    onSuccess() // Navegamos atrás o mostramos mensaje
                }
                is BaseResult.Error -> {

                    errorMessage = "Error al guardar el planeta"
                }
            }
        }
    }
}