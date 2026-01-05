package com.example.menu_alta_listadoplanetstarwars.viewModel

import Planet
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.menu_alta_listadoplanetstarwars.data.repository.PlanetRepositorio
import com.example.menu_alta_listadoplanetstarwars.network.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AñadirViewModel @Inject constructor(private  val repositorio: PlanetRepositorio) : ViewModel() {
    var name by mutableStateOf("")
    var rotationPeriod by mutableStateOf("")
    var orbitalPeriod by mutableStateOf("")
    var diameter by mutableStateOf("")
    var climate by mutableStateOf("")
    var gravity by mutableStateOf("")
    var terrain by mutableStateOf("")
    var surfaceWater by mutableStateOf("")
    var population by mutableStateOf("")
    fun resetear(){
        name = ""; rotationPeriod = ""; orbitalPeriod = ""; diameter = ""
        climate = ""; gravity = ""; terrain = ""; surfaceWater = ""; population = ""
    }
    fun insertarPlaneta(succes:() -> Unit){
        val planetaNuevo = Planet(
            id = (10..9999).random(), // Generamos un ID aleatorio para la simulación
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
        if(result is BaseResult.Sucess){
            resetear()
            succes()
        }
    }

}