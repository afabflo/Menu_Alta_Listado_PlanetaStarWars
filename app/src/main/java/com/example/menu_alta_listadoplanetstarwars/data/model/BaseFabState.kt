package com.example.menu_alta_listadoplanetstarwars.data.model

// Data class necesaria para el estado del FAB
data class BaseFabState(
    val icon: Int? = null,
    val isVisible: Boolean = false,
    val action: () -> Unit = {}
)