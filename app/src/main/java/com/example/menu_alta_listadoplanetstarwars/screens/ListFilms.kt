package com.example.menu_alta_listadoplanetstarwars.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBarState
import com.example.menu_alta_listadoplanetstarwars.data.model.Film
import com.example.menu_alta_listadoplanetstarwars.home.Routes
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import com.example.menu_alta_listadoplanetstarwars.viewModel.FilmViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListFilms(
    modifier: Modifier = Modifier,
    viewModel: FilmViewModel,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    onUpdateTopBar: (BaseTopAppBarState) -> Unit
) {
    val films by viewModel.films.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<Film?>(null) }
    val scope = rememberCoroutineScope()

    val filteredFilms = films.filter {
        it.title.contains(viewModel.search, ignoreCase = true)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(8.dp)
    ) {

        OutlinedTextField(
            value = viewModel.search,
            onValueChange = { viewModel.onSearchChange(it) },
            label = { Text("Buscar película") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredFilms) { film ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .combinedClickable(
                            onClick = {
                                viewModel.seleccionarPelicula(film)
                                navController.navigate(Routes.editFilm(film.filmId))
                            },
                            onLongClick = {
                                itemToDelete = film
                                showDialog = true
                            }
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF151515)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = film.title,
                            fontSize = 22.sp,
                            color = Color.White
                        )

                        Text(
                            text = "Director: ${film.director}",
                            color = Color.LightGray
                        )

                        Text(
                            text = "Episodio: ${film.episode}",
                            color = colorWars
                        )

                        Text(
                            text = "Estreno: ${film.releaseDate}",
                            color = Color.LightGray
                        )
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Eliminar Película") },
            text = { Text("¿Deseas borrar ${itemToDelete?.title}?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        itemToDelete?.let {
                            viewModel.borrarPelicula(it)
                            scope.launch {
                                snackbarHostState.showSnackbar("Película ${it.title} eliminada")
                            }
                        }
                        showDialog = false
                    }
                ) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}