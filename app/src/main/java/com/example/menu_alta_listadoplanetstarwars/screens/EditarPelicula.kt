package com.example.menu_alta_listadoplanetstarwars.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import com.example.menu_alta_listadoplanetstarwars.viewModel.FilmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPelicula(
    navController: NavController,
    filmId: Int,
    viewModel: FilmViewModel = hiltViewModel()
) {
    LaunchedEffect(filmId) {
        viewModel.cargarPeliculaPorId(filmId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Editar película") },
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
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            OutlinedTextField(
                value = viewModel.filmId,
                onValueChange = {},
                enabled = false,
                label = { Text("Film ID") },
                modifier = Modifier.fillMaxWidth()
            )

            CampoPelicula("Título", viewModel.title) { viewModel.title = it }
            CampoPelicula("Episodio", viewModel.episode) { viewModel.episode = it }
            CampoPelicula("Director", viewModel.director) { viewModel.director = it }
            CampoPelicula("Fecha estreno", viewModel.releaseDate) { viewModel.releaseDate = it }
            CampoPelicula("Era", viewModel.era) { viewModel.era = it }
            CampoPelicula("Rating", viewModel.rating) { viewModel.rating = it }
            CampoPelicula("Texto apertura", viewModel.openingText) { viewModel.openingText = it }

            Row {
                Checkbox(
                    checked = viewModel.isOriginalTrilogy,
                    onCheckedChange = { viewModel.isOriginalTrilogy = it }
                )
                Text(
                    text = "Trilogía original",
                    color = Color.White
                )
            }

            viewModel.errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red
                )
            }

            Button(
                onClick = {
                    viewModel.actualizarPelicula {
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = colorWars)
            ) {
                Text("Actualizar Película", color = Color.Black)
            }
        }
    }
}

@Composable
fun CampoPelicula(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
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