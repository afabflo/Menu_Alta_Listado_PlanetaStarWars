package com.example.menu_alta_listadoplanetstarwars.data.repository

import Planet

object ListaPlanetas {

    val planetas = listOf(
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
