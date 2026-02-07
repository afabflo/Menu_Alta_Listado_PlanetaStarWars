package com.example.menu_alta_listadoplanetstarwars.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.menu_alta_listadoplanetstarwars.R
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBarState
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseFabState // Asegúrate de importar tu modelo
import com.example.menu_alta_listadoplanetstarwars.data.permission.AppPermissions
import com.example.menu_alta_listadoplanetstarwars.data.util.NotificationHelper
import com.example.menu_alta_listadoplanetstarwars.helper.rememberPermissionsLauncher
import com.example.menu_alta_listadoplanetstarwars.ui.components.CampoTextoPlaneta
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import com.example.menu_alta_listadoplanetstarwars.viewModel.AñadirViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AñadirPlaneta(
    modifier: Modifier,
    navHostController: NavHostController,
    onUpdateTopBar: (BaseTopAppBarState) -> Unit,
    onUpdateFab: (BaseFabState) -> Unit,
    viewModel: AñadirViewModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current



    val requestNotificationPermissionThenNotify = rememberPermissionsLauncher(
        permissions = listOf(AppPermissions.Notifications),
        onAllGranted = {
            onBack()
            viewModel.resetSuccess()
        },
        onDenied = {
            onBack()
            viewModel.resetSuccess()
        }
    )


    LaunchedEffect(viewModel.isSuccess) {
        if (viewModel.isSuccess) {
            requestNotificationPermissionThenNotify()
        }
    }

    if (viewModel.showDuplicatedDialog) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { viewModel.showDuplicatedDialog = false },
            confirmButton = {
                androidx.compose.material3.TextButton(
                    onClick = { viewModel.showDuplicatedDialog = false }
                ) {
                    Text("Aceptar", color = colorWars)
                }
            },
            title = { Text("Planeta Duplicado") },
            text = { Text("El planeta '${viewModel.name}' ya existe en la base de datos. Por favor, elige otro nombre.") }
        )
    }

    val backIcon = painterResource(id = R.drawable.ic_launcher)
    LaunchedEffect(Unit) {
        onUpdateTopBar(
            BaseTopAppBarState(
                title = "Nuevo planeta",
                iconUpAction = backIcon,
                upAction = { onBack() },
                actions = emptyList()
            )
        )

        onUpdateFab(
            BaseFabState(
                icon = android.R.drawable.ic_menu_save,
                isVisible = true,
                action = { viewModel.insertarPlaneta() }
            )
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            onUpdateFab(BaseFabState(isVisible = true, icon = null))
        }
    }

    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HeaderBox()

        // Campos de formulario
        CampoTextoPlaneta(viewModel.name, { viewModel.name = it }, "Nombre")

        // Mostrar error de validaciomn
        if (viewModel.errorMessage != null) {
            Text(
                text = viewModel.errorMessage!!,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        CampoTextoPlaneta(viewModel.rotationPeriod, { viewModel.rotationPeriod = it }, "Periodo de rotación")
        CampoTextoPlaneta(viewModel.orbitalPeriod, { viewModel.orbitalPeriod = it }, "Periodo orbital")
        CampoTextoPlaneta(viewModel.diameter, { viewModel.diameter = it }, "Diámetro")
        CampoTextoPlaneta(viewModel.climate, { viewModel.climate = it }, "Clima")
        CampoTextoPlaneta(viewModel.gravity, { viewModel.gravity = it }, "Gravedad")
        CampoTextoPlaneta(viewModel.terrain, { viewModel.terrain = it }, "Terreno")
        CampoTextoPlaneta(viewModel.surfaceWater, { viewModel.surfaceWater = it }, "Agua superficial")
        CampoTextoPlaneta(viewModel.population, { viewModel.population = it }, "Población")

        Button(
            onClick = { viewModel.insertarPlaneta() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorWars)
        ) {
            Text(text = "Añadir Planeta", color = Color.Black)
        }
    }
}
@Composable
fun HeaderBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.planetaswars),
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentDescription = "Mapa de galaxia de Star Wars",
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.7f))
                .padding(vertical = 4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.starwarsTitleFoto),
                fontSize = 22.sp,
                color = colorWars,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}