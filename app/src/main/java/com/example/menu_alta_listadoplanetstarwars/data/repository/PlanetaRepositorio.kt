package com.example.menu_alta_listadoplanetstarwars.data.repository

import com.example.menu_alta_listadoplanetstarwars.data.dao.PlanetDAO
import com.example.menu_alta_listadoplanetstarwars.model.Planet
import com.example.menu_alta_listadoplanetstarwars.model.PlanetException
import com.example.menu_alta_listadoplanetstarwars.network.BaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanetRepositorio @Inject constructor(
    private val planetDAO: PlanetDAO
) {

    var planetaSeleccionado: Planet? = null

    fun getDataFlow(): Flow<List<Planet>> = planetDAO.getAllFlow()

    fun getPlanetsOrderAsc(): Flow<List<Planet>> = planetDAO.getOrderByASC()

    fun getPlanetsOrderDesc(): Flow<List<Planet>> = planetDAO.getOrderByDESC()

    fun searchPlanets(search: String): Flow<List<Planet>> = planetDAO.searchPlanets(search)

    suspend fun add(planet: Planet): BaseResult<Planet> {
        return withContext(Dispatchers.IO) {
            val exists = planetDAO.existsByName(planet.name)

            if (exists) {
                BaseResult.Error(PlanetException.Exists)
            } else {
                planetDAO.insert(planet)
                BaseResult.Sucess(planet)
            }
        }
    }

    suspend fun update(updatePlanet: Planet): BaseResult<Planet> {
        return withContext(Dispatchers.IO) {
            val rowsAffected = planetDAO.update(updatePlanet)

            if (rowsAffected > 0) {
                BaseResult.Sucess(updatePlanet)
            } else {
                BaseResult.Error(PlanetException.NotFound)
            }
        }
    }

    suspend fun delete(planet: Planet) {
        withContext(Dispatchers.IO) {
            planetDAO.delete(planet)
        }
    }

    suspend fun getPlanetByName(name: String): Planet? {
        return withContext(Dispatchers.IO) {
            planetDAO.getPlanetByName(name)
        }
    }
}