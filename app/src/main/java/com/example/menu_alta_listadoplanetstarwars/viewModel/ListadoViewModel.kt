package com.example.menu_alta_listadoplanetstarwars.viewModel

import Planet
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.menu_alta_listadoplanetstarwars.data.repository.PlanetRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListadoViewModel @Inject constructor(
    private val repository : PlanetRepositorio
)  : ViewModel(){
    val planetas : Flow<List<Planet>> = repository.getDataFlow()
    fun borrarPlaneta(planet: Planet){
        repository.delete(planet)
    }
    //Esto va a ser para que se pueda editar antes de navegar
    fun selecionarPlaneta(planet: Planet){
        repository.planetaSeleccionado = planet
    }
}