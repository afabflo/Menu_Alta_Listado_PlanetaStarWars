package com.example.menu_alta_listadoplanetstarwars.data.permission

import android.os.Build

/**
 * Representa un permiso "runtime" que puede depender de una versión mínima.
 */
data class AppRuntimePermission(
    val permission: String,
    val minSdk: Int = Build.VERSION_CODES.M // Por defecto, runtime desde M (23)
) {
    fun appliesToDevice(): Boolean = Build.VERSION.SDK_INT >= minSdk
}