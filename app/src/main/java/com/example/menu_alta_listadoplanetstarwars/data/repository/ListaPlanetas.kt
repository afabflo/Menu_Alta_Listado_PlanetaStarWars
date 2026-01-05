package com.example.menu_alta_listadoplanetstarwars.data.repository

import Planet
import com.example.menu_alta_listadoplanetstarwars.model.PlanetException
import com.example.menu_alta_listadoplanetstarwars.network.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanetRepositorio @Inject constructor() {

    private val dataset = mutableListOf<Planet>();
    private val planetas = MutableStateFlow<List<Planet>>(emptyList())
    var planetaSeleccionado:Planet? = null
    init{
        initData()
        notifyChage()
    }
    private fun notifyChage(){
        planetas.value = dataset.toList()
    }
    //Recoger datos
    fun getDataFlow() : Flow<List<Planet>> = planetas

    fun add(planet: Planet) : BaseResult<Planet>{
        val exist = dataset.any{it.id == planet.id}
        if(exist){
          return  BaseResult.Error(PlanetException.Exists)
        }else{
            dataset.add(planet)
            notifyChage()
           return  BaseResult.Sucess(planet)
        }
        }

    fun update(updatePlanet:Planet) : BaseResult<Planet>{
        val index = dataset.indexOfFirst { it.id == updatePlanet.id }
        if(index != -1){
            dataset[index] = updatePlanet
            notifyChage()
            return BaseResult.Sucess(updatePlanet)
        }
        else {
            return BaseResult.Error(PlanetException.NotFound)
        }
    }
    fun delete(planet: Planet){
        dataset.removeIf{it.id == planet.id}
        notifyChage()
    }

    private fun initData(){

        dataset.add(
            Planet(
                id = 1,
                name = "Tatooine",
                rotation_period = "23",
                orbital_period = "304",
                diameter = "10465",
                climate = "arid",
                gravity = "1 standard",
                terrain = "desert",
                surface_water = "1",
                population = "200000",
                residents = listOf(
                    "Luke Skywalker",
                    "Anakin Skywalker",
                    "Shmi Skywalker"
                ),
                films = listOf(
                    "A New Hope",
                    "Return of the Jedi",
                    "The Phantom Menace",
                    "Attack of the Clones",
                    "Revenge of the Sith"
                )
            )
        )

          dataset.add(Planet(
                id = 2,
                name = "Alderaan",
                rotation_period = "24",
                orbital_period = "364",
                diameter = "12500",
                climate = "temperate",
                gravity = "1 standard",
                terrain = "grasslands, mountains",
                surface_water = "40",
                population = "2000000000",
                residents = listOf("Leia Organa", "Bail Organa"),
                films = listOf("A New Hope", "Revenge of the Sith")
            )
          )
            dataset.add(Planet(
                id = 3,
                name = "Yavin IV",
                rotation_period = "24",
                orbital_period = "4818",
                diameter = "10200",
                climate = "temperate, tropical",
                gravity = "1 standard",
                terrain = "jungle, rainforests",
                surface_water = "8",
                population = "1000",
                residents = emptyList(),
                films = listOf("A New Hope")
            )
            )
            dataset.add(Planet(
                id = 4,
                name = "Hoth",
                rotation_period = "23",
                orbital_period = "549",
                diameter = "7200",
                climate = "frozen",
                gravity = "1.1 standard",
                terrain = "tundra, ice caves, mountain ranges",
                surface_water = "100",
                population = "unknown",
                residents = emptyList(),
                films = listOf("The Empire Strikes Back")
            )
            )

        dataset.add(Planet(
                id = 5,
                name = "Dagobah",
                rotation_period = "23",
                orbital_period = "341",
                diameter = "8900",
                climate = "murky",
                gravity = "N/A",
                terrain = "swamp, jungles",
                surface_water = "8",
                population = "unknown",
                residents = emptyList(),
                films = listOf(
                    "The Empire Strikes Back",
                    "Return of the Jedi"
                )
            )
        )
            dataset.add(Planet(
                id = 6,
                name = "Bespin",
                rotation_period = "12",
                orbital_period = "5110",
                diameter = "118000",
                climate = "temperate",
                gravity = "1.5 (surface), 1 (cloud city)",
                terrain = "gas giant",
                surface_water = "0",
                population = "6000000",
                residents = listOf("Lando Calrissian"),
                films = listOf("The Empire Strikes Back")
            )
            )
           dataset.add(Planet(
                id = 7,
                name = "Endor",
                rotation_period = "18",
                orbital_period = "402",
                diameter = "4900",
                climate = "temperate",
                gravity = "0.85 standard",
                terrain = "forests, mountains, lakes",
                surface_water = "8",
                population = "30000000",
                residents = listOf("Wicket W. Warrick"),
                films = listOf("Return of the Jedi")
            )
        )

    }
    val planetas2 = listOf(
        Planet(
            id = 1,
            name = "Tatooine",
            rotation_period = "23",
            orbital_period = "304",
            diameter = "10465",
            climate = "arid",
            gravity = "1 standard",
            terrain = "desert",
            surface_water = "1",
            population = "200000",
            residents = listOf(
                "Luke Skywalker",
                "Anakin Skywalker",
                "Shmi Skywalker"
            ),
            films = listOf(
                "A New Hope",
                "Return of the Jedi",
                "The Phantom Menace",
                "Attack of the Clones",
                "Revenge of the Sith"
            )
        ),

        Planet(
            id = 2,
            name = "Alderaan",
            rotation_period = "24",
            orbital_period = "364",
            diameter = "12500",
            climate = "temperate",
            gravity = "1 standard",
            terrain = "grasslands, mountains",
            surface_water = "40",
            population = "2000000000",
            residents = listOf("Leia Organa", "Bail Organa"),
            films = listOf("A New Hope", "Revenge of the Sith")
        ),

        Planet(
            id = 3,
            name = "Yavin IV",
            rotation_period = "24",
            orbital_period = "4818",
            diameter = "10200",
            climate = "temperate, tropical",
            gravity = "1 standard",
            terrain = "jungle, rainforests",
            surface_water = "8",
            population = "1000",
            residents = emptyList(),
            films = listOf("A New Hope")
        ),

        Planet(
            id = 4,
            name = "Hoth",
            rotation_period = "23",
            orbital_period = "549",
            diameter = "7200",
            climate = "frozen",
            gravity = "1.1 standard",
            terrain = "tundra, ice caves, mountain ranges",
            surface_water = "100",
            population = "unknown",
            residents = emptyList(),
            films = listOf("The Empire Strikes Back")
        ),

        Planet(
            id = 5,
            name = "Dagobah",
            rotation_period = "23",
            orbital_period = "341",
            diameter = "8900",
            climate = "murky",
            gravity = "N/A",
            terrain = "swamp, jungles",
            surface_water = "8",
            population = "unknown",
            residents = emptyList(),
            films = listOf(
                "The Empire Strikes Back",
                "Return of the Jedi"
            )
        ),

        Planet(
            id = 6,
            name = "Bespin",
            rotation_period = "12",
            orbital_period = "5110",
            diameter = "118000",
            climate = "temperate",
            gravity = "1.5 (surface), 1 (cloud city)",
            terrain = "gas giant",
            surface_water = "0",
            population = "6000000",
            residents = listOf("Lando Calrissian"),
            films = listOf("The Empire Strikes Back")
        ),

        Planet(
            id = 7,
            name = "Endor",
            rotation_period = "18",
            orbital_period = "402",
            diameter = "4900",
            climate = "temperate",
            gravity = "0.85 standard",
            terrain = "forests, mountains, lakes",
            surface_water = "8",
            population = "30000000",
            residents = listOf("Wicket W. Warrick"),
            films = listOf("Return of the Jedi")
        )
    )
}
