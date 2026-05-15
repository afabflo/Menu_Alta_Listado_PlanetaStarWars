package com.example.menu_alta_listadoplanetstarwars.screens

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.composables.deathstaricon
import com.example.menu_alta_listadoplanetstarwars.data.model.Action
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseFabState
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBarState
import com.example.menu_alta_listadoplanetstarwars.home.Routes
import com.example.menu_alta_listadoplanetstarwars.model.Planet
import com.example.menu_alta_listadoplanetstarwars.ui.theme.Menu_Alta_ListadoPlanetStarWarsTheme
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
    onUpdateTopBar: (BaseTopAppBarState) -> Unit,
    onUpdateFab: (BaseFabState) -> Unit
) {
    val planetas by viewModel.planetas.collectAsState(initial = emptyList())

    var showDialog by remember { mutableStateOf(false) }
    var planetToDelete by remember { mutableStateOf<Planet?>(null) }

    val scope = rememberCoroutineScope()
    val menuIcon = painterResource(id = com.example.menu_alta_listadoplanetstarwars.R.drawable.ic_launcher)

    LaunchedEffect(Unit) {
        onUpdateTopBar(
            BaseTopAppBarState(
                title = "Planetas Star Wars",
                iconUpAction = menuIcon,
                actions = listOf(
                    Action.ActionImageVector(
                        name = "Sobre nosotros",
                        icon = Icons.Default.Info,
                        contentDescription = "Informacion",
                        onClick = { navController.navigate(Routes.ABOUT) },
                        isVisible = true
                    )
                )
            )
        )

        onUpdateFab(
            BaseFabState(
                isVisible = true,
                icon = null,
                action = { navController.navigate(Routes.ADD) }
            )
        )
    }

    Column(modifier = modifier.fillMaxSize()) {

        OutlinedTextField(
            value = viewModel.planetSearch,
            onValueChange = { viewModel.planetSearch = it },
            label = { Text("Buscar planeta") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
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
                    colors = CardDefaults.cardColors(containerColor = Color.Black)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = deathstaricon,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = item.name,
                                style = TextStyle(color = colorWars, fontSize = 20.sp)
                            )
                            Text(
                                text = "Clima: ${item.climate}",
                                style = TextStyle(color = Color.White)
                            )
                            Text(
                                text = "Población: ${item.population}",
                                style = TextStyle(color = Color.White)
                            )
                            Text(
                                text = "ID: ${item.id}",
                                style = TextStyle(color = colorWars, fontSize = 14.sp)
                            )
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Confirmar eliminación") },
            text = {
                Text(text = "¿Seguro que quieres borrar el planeta ${planetToDelete?.name}?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val nombreBorrado = planetToDelete?.name ?: ""
                        planetToDelete?.let { viewModel.borrarPlaneta(it) }
                        showDialog = false

                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "El planeta $nombreBorrado ha sido eliminado"
                            )
                        }
                    }
                ) {
                    Text("Borrar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "Cancelar", color = colorWars)
                }
            }
        )
    }
}

@Preview(showBackground = true, name = "Modo Claro")
@Composable
fun ListPlanetsPreviewLight() {
    Menu_Alta_ListadoPlanetStarWarsTheme(darkTheme = false) {
        Surface {
            Text(
                "Preview de la lista de planetas (Modo Claro)",
                Modifier.padding(20.dp)
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Modo Oscuro"
)
@Composable
fun ListPlanetsPreviewDark() {
    Menu_Alta_ListadoPlanetStarWarsTheme(darkTheme = true) {
        Surface {
            Text(
                "Preview de la lista de planetas (Modo Oscuro)",
                Modifier.padding(20.dp)
            )
        }
    }
}