package com.example.menu_alta_listadoplanetstarwars.data.permission

import android.Manifest
import android.os.Build

object AppPermissions {

    val Notifications = AppRuntimePermission(
        permission = Manifest.permission.POST_NOTIFICATIONS,
        minSdk = Build.VERSION_CODES.TIRAMISU // API 33 (Android 13)
    )

    val Camera = AppRuntimePermission(
        permission = Manifest.permission.CAMERA,
        minSdk = Build.VERSION_CODES.M // API 23 (Android 6)
    )

    // Lista de conveniencia para pedir varios a la vez
    val allPermissions = listOf(Notifications, Camera)
}