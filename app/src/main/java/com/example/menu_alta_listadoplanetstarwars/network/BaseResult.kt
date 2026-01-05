package com.example.menu_alta_listadoplanetstarwars.network

import com.example.menu_alta_listadoplanetstarwars.model.PlanetException

sealed class BaseResult<out T> {
    data class Sucess<T> (var data:T) : BaseResult<T>()
    data class Error(val exception: PlanetException) : BaseResult<Nothing>()
}