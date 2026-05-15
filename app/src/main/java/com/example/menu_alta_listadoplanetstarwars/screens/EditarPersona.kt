package com.example.menu_alta_listadoplanetstarwars.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import com.example.menu_alta_listadoplanetstarwars.viewModel.PersonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPersona(
    navController: NavController,
    personId: Int,
    viewModel: PersonViewModel = hiltViewModel()
) {
    LaunchedEffect(personId) {
        viewModel.cargarPersonaPorId(personId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Editar personaje") },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.limpiarCampos()
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
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
            CampoPersona("Nombre", viewModel.name) { viewModel.name = it }
            CampoPersona("Altura", viewModel.height) { viewModel.height = it }
            CampoPersona("Peso", viewModel.mass) { viewModel.mass = it }
            CampoPersona("Color de pelo", viewModel.hairColor) { viewModel.hairColor = it }
            CampoPersona("Color de piel", viewModel.skinColor) { viewModel.skinColor = it }
            CampoPersona("Color de ojos", viewModel.eyeColor) { viewModel.eyeColor = it }
            CampoPersona("Año nacimiento", viewModel.birthYear) { viewModel.birthYear = it }
            CampoPersona("Género", viewModel.gender) { viewModel.gender = it }
            CampoPersona("ID planeta", viewModel.planetId) { viewModel.planetId = it }

            viewModel.errorMessage?.let {
                Text(text = it, color = Color.Red)
            }

            Button(
                onClick = {
                    viewModel.actualizarPersona {
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = colorWars)
            ) {
                Text("Actualizar personaje", color = Color.Black)
            }
        }
    }
}