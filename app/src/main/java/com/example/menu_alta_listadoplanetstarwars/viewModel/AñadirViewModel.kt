package com.example.menu_alta_listadoplanetstarwars.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menu_alta_listadoplanetstarwars.data.repository.PlanetRepositorio
import com.example.menu_alta_listadoplanetstarwars.data.util.NotificationHelper
import com.example.menu_alta_listadoplanetstarwars.model.Planet
import com.example.menu_alta_listadoplanetstarwars.network.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AñadirViewModel @Inject constructor(
    private val repositorio: PlanetRepositorio,
    private val notificationHelper: NotificationHelper
) : ViewModel() {


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


    var showDuplicatedDialog by mutableStateOf(false)

    // Estado de éxito
    var isSuccess by mutableStateOf(false)
        private set

    fun resetear() {
        name = ""; rotationPeriod = ""; orbitalPeriod = ""; diameter = ""
        climate = ""; gravity = ""; terrain = ""; surfaceWater = ""; population = ""
        errorMessage = null
        isSuccess = false
        showDuplicatedDialog = false
    }

    fun resetSuccess() {
        isSuccess = false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertarPlaneta() {

        if (name.isBlank()) {
            errorMessage = "El nombre es obligatorio"
            return
        }

        viewModelScope.launch {

            val existe = repositorio.getPlanetByName(name)

            if (existe != null) {
                showDuplicatedDialog = true
            } else {
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
                        // Se lanza cuando la operación ha sido exitosa
                        notificationHelper.showSimpleNotification(
                            "Planeta Guardado",
                            "Se ha registrado con éxito el planeta: $name"
                        )
                        isSuccess = true
                    }
                    is BaseResult.Error -> {
                        errorMessage = "Error al guardar el planeta en la base de datos"
                        isSuccess = false
                    }
                }
            }
        }
    }
}