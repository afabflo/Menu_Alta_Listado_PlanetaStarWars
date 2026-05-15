package com.example.menu_alta_listadoplanetstarwars.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.menu_alta_listadoplanetstarwars.data.permission.AppPermissions
import com.example.menu_alta_listadoplanetstarwars.data.util.NotificationHelper
import com.example.menu_alta_listadoplanetstarwars.helper.rememberPermissionsLauncher
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import com.example.menu_alta_listadoplanetstarwars.viewModel.PersonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AñadirPersona(
    navController: NavController,
    viewModel: PersonViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val notificationHelper = NotificationHelper(context)

    val requestNotificationPermissionThenNotify = rememberPermissionsLauncher(
        permissions = listOf(AppPermissions.Notifications),
        onAllGranted = {
            notificationHelper.showSimpleNotification(
                "Nuevo personaje añadido",
                "Personaje guardado correctamente"
            )
            viewModel.limpiarCampos()
            navController.popBackStack()
        },
        onDenied = {
            viewModel.limpiarCampos()
            navController.popBackStack()
        }
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Añadir personaje")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            viewModel.limpiarCampos()
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            CampoPersona(
                label = "Nombre",
                value = viewModel.name,
                onValueChange = { viewModel.name = it }
            )

            CampoPersona(
                label = "Altura",
                value = viewModel.height,
                onValueChange = { viewModel.height = it }
            )

            CampoPersona(
                label = "Peso",
                value = viewModel.mass,
                onValueChange = { viewModel.mass = it }
            )

            CampoPersona(
                label = "Color de pelo",
                value = viewModel.hairColor,
                onValueChange = { viewModel.hairColor = it }
            )

            CampoPersona(
                label = "Color de piel",
                value = viewModel.skinColor,
                onValueChange = { viewModel.skinColor = it }
            )

            CampoPersona(
                label = "Color de ojos",
                value = viewModel.eyeColor,
                onValueChange = { viewModel.eyeColor = it }
            )

            CampoPersona(
                label = "Año nacimiento",
                value = viewModel.birthYear,
                onValueChange = { viewModel.birthYear = it }
            )

            CampoPersona(
                label = "Género",
                value = viewModel.gender,
                onValueChange = { viewModel.gender = it }
            )

            CampoPersona(
                label = "ID planeta",
                value = viewModel.planetId,
                onValueChange = { viewModel.planetId = it }
            )

            viewModel.errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red
                )
            }

            Button(
                onClick = {
                    viewModel.añadirPersona { nombreGuardado ->
                        requestNotificationPermissionThenNotify()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorWars
                )
            ) {
                Text(
                    text = "Guardar personaje",
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun CampoPersona(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label)
        },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedBorderColor = colorWars,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = colorWars,
            unfocusedLabelColor = Color.LightGray,
            cursorColor = colorWars
        )
    )
}