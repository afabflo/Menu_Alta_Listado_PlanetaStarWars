package com.example.menu_alta_listadoplanetstarwars.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.menu_alta_listadoplanetstarwars.home.Routes
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import com.example.menu_alta_listadoplanetstarwars.viewModel.PersonViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListPeople(
    viewModel: PersonViewModel,
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {
    val people by viewModel.people.collectAsState()
    val scope = rememberCoroutineScope()

    val filteredPeople = people.filter {
        it.name.contains(viewModel.search, ignoreCase = true)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.limpiarCampos()
                    navController.navigate(Routes.ADD_PERSON)
                },
                containerColor = colorWars
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir personaje",
                    tint = Color.Black
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Personajes Star Wars",
                style = MaterialTheme.typography.headlineSmall,
                color = colorWars
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = viewModel.search,
                onValueChange = { viewModel.onSearchChange(it) },
                label = { Text("Buscar personaje") },
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

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredPeople) { person ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .combinedClickable(
                                onClick = {
                                    viewModel.seleccionarPersona(person)
                                    navController.navigate(Routes.editPerson(person.id))
                                },
                                onLongClick = {
                                    viewModel.borrarPersona(person)
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            "Personaje eliminado"
                                        )
                                    }
                                }
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF151515)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {

                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = colorWars,
                                modifier = Modifier.size(42.dp)
                            )

                            Spacer(modifier = Modifier.width(14.dp))

                            Column {
                                Text(
                                    text = person.name,
                                    color = Color.White,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    text = "Género: ${person.gender}",
                                    color = Color.LightGray
                                )

                                Text(
                                    text = "Altura: ${person.height}",
                                    color = Color.LightGray
                                )

                                Text(
                                    text = "Planeta ID: ${person.planetId}",
                                    color = Color.LightGray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}