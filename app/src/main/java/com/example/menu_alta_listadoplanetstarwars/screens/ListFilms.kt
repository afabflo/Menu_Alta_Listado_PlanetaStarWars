package com.example.menu_alta_listadoplanetstarwars.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBarState
import com.example.menu_alta_listadoplanetstarwars.data.model.Film
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
    val films by viewModel.films.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<Film?>(null) }
    val scope = rememberCoroutineScope()

    // Fondo nacarado azul galáctico
    val pearlyGradient = androidx.compose.ui.graphics.Brush.linearGradient(
        colors = listOf(Color(0xFF1A237E), Color(0xFF5C6BC0), Color(0xFFE8EAF6))
    )

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(films) { film ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .combinedClickable(
                        onClick = { /* Navegar a edición */ },
                        onLongClick = {
                            itemToDelete = film
                            showDialog = true
                        }
                    ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Box(modifier = Modifier.background(pearlyGradient).padding(16.dp)) {
                    Column {
                        Text(film.title, fontSize = 22.sp, color = Color.White, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                        Text("Director: ${film.director}", color = Color.White.copy(alpha = 0.8f))
                        Text("Episodio: ${film.episode}", color = colorWars)
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
                TextButton(onClick = {
                    itemToDelete?.let { viewModel.borrarPelicula(it) }
                    showDialog = false
                    scope.launch { snackbarHostState.showSnackbar("Película eliminada") }
                }) { Text("Eliminar", color = Color.Red) }
            },
            dismissButton = { TextButton(onClick = { showDialog = false }) { Text("Cancelar") } }
        )
    }
}