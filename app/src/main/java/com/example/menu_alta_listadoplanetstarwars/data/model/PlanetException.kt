package com.example.menu_alta_listadoplanetstarwars.model

sealed class PlanetException(val reason :String) : Exception() {
    data object  NotFound : PlanetException("Planeta no encontrado")
    data object Exists : PlanetException("El planeta ya existe")
    data object None : PlanetException("")
}