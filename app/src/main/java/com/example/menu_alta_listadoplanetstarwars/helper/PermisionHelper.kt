package com.example.menu_alta_listadoplanetstarwars.helper


import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.menu_alta_listadoplanetstarwars.data.permission.AppRuntimePermission

/**
 * Pide VARIOS permisos a la vez.
 */
@Composable
fun rememberPermissionsLauncher(
    permissions: List<AppRuntimePermission>,
    onAllGranted: () -> Unit,
    onDenied: (denied: List<String>) -> Unit = {},
    onResultMap: (Map<String, Boolean>) -> Unit = {}
): () -> Unit {
    val context = LocalContext.current

    val permissionsToRequest = remember(permissions) {
        permissions.filter { it.appliesToDevice() }.map { it.permission }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        onResultMap(result)
        val denied = result.filterValues { granted -> !granted }.keys.toList()
        if (denied.isEmpty()) onAllGranted() else onDenied(denied)
    }

    return remember(permissionsToRequest) {
        {
            if (permissionsToRequest.isEmpty()) {
                onAllGranted()
            } else {
                val allGranted = permissionsToRequest.all { perm ->
                    ContextCompat.checkSelfPermission(context, perm) == PackageManager.PERMISSION_GRANTED
                }

                if (allGranted) {
                    onAllGranted()
                } else {
                    launcher.launch(permissionsToRequest.toTypedArray())
                }
            }
        }
    }
}