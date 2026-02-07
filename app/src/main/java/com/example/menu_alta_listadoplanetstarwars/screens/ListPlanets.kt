package com.example.menu_alta_listadoplanetstarwars.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.composables.deathstaricon // TU ICONO
import com.example.menu_alta_listadoplanetstarwars.data.model.Action
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBarState
import com.example.menu_alta_listadoplanetstarwars.home.Routes
import com.example.menu_alta_listadoplanetstarwars.model.Planet
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import com.example.menu_alta_listadoplanetstarwars.viewModel.ListadoViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ListPlanets(
    modifier: Modifier = Modifier,
    viewModel: ListadoViewModel,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    onUpdateTopBar: (BaseTopAppBarState) -> Unit
) {
    val planetas by viewModel.planetas.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var planetToDelete by remember { mutableStateOf<Planet?>(null) }
    val scope = rememberCoroutineScope()
    val menuIcon = painterResource(id = com.example.menu_alta_listadoplanetstarwars.R.drawable.ic_launcher)
    LaunchedEffect(Unit) {
        onUpdateTopBar(
            BaseTopAppBarState(
                title = "Planeta Star Wars",
                iconUpAction = menuIcon,
                actions = listOf(
                    Action.ActionImageVector(
                        name = "Añadir",
                        icon = Icons.Default.Add,
                        contentDescription = "Añadir Planeta",
                        onClick = {navController.navigate(Routes.ADD)},
                        isVisible = true
                    ),
                    Action.ActionImageVector(
                        name = "Sobre nosotros",
                        icon = Icons.Default.Info,
                        contentDescription = "Informacion",
                        onClick = {navController.navigate(Routes.ABOUT)},
                        isVisible = false
                    )
                )
            )
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(planetas) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 12.dp)
                    .combinedClickable(
                        onClick = {
                            viewModel.selecionarPlaneta(item)
                            navController.navigate(Routes.EDIT)
                        },
                        onLongClick = {
                            planetToDelete = item
                            showDialog = true
                        }
                    ),
                shape = CardDefaults.shape,
                colors = CardDefaults.cardColors(containerColor = Color.Black),
            ) {
                androidx.compose.foundation.layout.Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = deathstaricon,
                        contentDescription = "Estrella de la muerte${item.name}",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = item.name, style = TextStyle(color = colorWars, fontSize = 20.sp))
                        Text(text = "Clima: ${item.climate}", style = TextStyle(color = Color.White))
                        Text(text = "Población: ${item.population}", style = TextStyle(color = Color.White))
                        Text(text = "ID: ${item.id}", style = TextStyle(color = colorWars, fontSize = 20.sp))
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Eliminar Planeta") },
            text = { Text(text = "¿Borrar ${planetToDelete?.name}?") },
            confirmButton = {
                TextButton(onClick = {
                    planetToDelete?.let { viewModel.borrarPlaneta(it) }
                    showDialog = false
                    scope.launch {
                        snackbarHostState.showSnackbar("Eliminado correctamente")
                    }
                }) { Text("Eliminar") }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "Cancelar", color = colorWars)
                }
            }
        )
    }
}