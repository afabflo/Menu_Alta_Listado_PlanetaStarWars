package com.example.menu_alta_listadoplanetstarwars.data.repository

import com.example.menu_alta_listadoplanetstarwars.model.Planet
import com.example.menu_alta_listadoplanetstarwars.data.dao.PlanetDAO
import com.example.menu_alta_listadoplanetstarwars.model.PlanetException
import com.example.menu_alta_listadoplanetstarwars.network.BaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanetRepositorio @Inject constructor(private val planetDAO: PlanetDAO) {

    // Variable para guardar el planeta que el usuario pulsa en la lista
    var planetaSeleccionado: Planet? = null

    /**
     * Devuelve un Flow directamente desde la base de datos.
     * Al ser un Flow, cualquier cambio en la DB (insert/delete)
     * se notificará automáticamente a la UI.
     */


    fun getPlanetByid(planet: Planet){
        //return planetDAO.getPlanetbyId(planet.id)
    }
    fun getDataFlow(): Flow<List<Planet>> = planetDAO.getAllFlow()

    // Operación de inserción
    suspend fun add(planet: Planet): BaseResult<Planet> {
        return withContext(Dispatchers.IO) {
            if (!planetDAO.exists(planet.id)) {
                planetDAO.insert(planet)
                BaseResult.Sucess(planet)
            } else {
                BaseResult.Error(PlanetException.Exists)
            }
        }
    }


    // Operación de actualización
    suspend fun update(updatePlanet: Planet): BaseResult<Planet> {

        val rowsAffected = planetDAO.update(updatePlanet)
        return if (rowsAffected > 0) {
            BaseResult.Sucess(updatePlanet)
        } else {
            BaseResult.Error(PlanetException.NotFound)
        }
    }
//deben ser suspend todas aquellas que devuelvan un dato como devolver planet by id
    // Operación de borrado
    suspend fun delete(planet: Planet) {
        withContext(Dispatchers.IO) {
            planetDAO.delete(planet)
        }
    }
}