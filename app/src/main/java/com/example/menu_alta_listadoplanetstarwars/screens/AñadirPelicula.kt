package com.example.menu_alta_listadoplanetstarwars.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.menu_alta_listadoplanetstarwars.viewModel.FilmViewModel

@Composable
fun AñadirPelicula(
    navController: NavController,
    viewModel: FilmViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        OutlinedTextField(viewModel.filmId, { viewModel.filmId = it }, label = { Text("Film ID") })
        OutlinedTextField(viewModel.title, { viewModel.title = it }, label = { Text("Título") })
        OutlinedTextField(viewModel.episode, { viewModel.episode = it }, label = { Text("Episodio") })
        OutlinedTextField(viewModel.director, { viewModel.director = it }, label = { Text("Director") })
        OutlinedTextField(viewModel.releaseDate, { viewModel.releaseDate = it }, label = { Text("Fecha estreno") })
        OutlinedTextField(viewModel.era, { viewModel.era = it }, label = { Text("Era") })
        OutlinedTextField(viewModel.rating, { viewModel.rating = it }, label = { Text("Rating") })
        OutlinedTextField(viewModel.openingText, { viewModel.openingText = it }, label = { Text("Texto apertura") })

        Row {
            Checkbox(
                checked = viewModel.isOriginalTrilogy,
                onCheckedChange = { viewModel.isOriginalTrilogy = it }
            )
            Text("Trilogía original")
        }

        Button(
            onClick = {
                viewModel.añadirPelicula {
                    navController.popBackStack()
                }
            }
        ) {
            Text("Guardar Película")
        }

        viewModel.errorMessage?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}