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

    // NUEVO: Estado de éxito para que la UI reaccione
    var isSuccess by mutableStateOf(false)
        private set

    fun resetear() {
        name = ""; rotationPeriod = ""; orbitalPeriod = ""; diameter = ""
        climate = ""; gravity = ""; terrain = ""; surfaceWater = ""; population = ""
        errorMessage = null
        isSuccess = false // También reseteamos el éxito
    }

    fun resetSuccess() {
        isSuccess = false
    }

    // CAMBIO: Ya no recibe el callback por parámetro
    fun insertarPlaneta() {
        if (name.isBlank()) {
            errorMessage = "El nombre es obligatorio"
            return
        }

        viewModelScope.launch {
            val planetaNuevo = Planet(
                id = 0, name = name, rotation_period = rotationPeriod,
                orbital_period = orbitalPeriod, diameter = diameter,
                climate = climate, gravity = gravity, terrain = terrain,
                surface_water = surfaceWater, population = population,
                residents = emptyList(), films = emptyList()
            )

            val result = repositorio.add(planetaNuevo)

            when (result) {
                is BaseResult.Sucess -> {
                    // Primero marcamos el éxito, esto disparará el LaunchedEffect en la UI
                    isSuccess = true
                }
                is BaseResult.Error -> {
                    errorMessage = "Error al guardar el planeta"
                    isSuccess = false
                }
            }
        }
    }
}