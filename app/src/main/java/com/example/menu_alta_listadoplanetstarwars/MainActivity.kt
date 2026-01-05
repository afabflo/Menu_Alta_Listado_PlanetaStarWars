package com.example.menu_alta_listadoplanetstarwars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.menu_alta_listadoplanetstarwars.home.NavHostScreen
import com.example.menu_alta_listadoplanetstarwars.home.Routes
import com.example.menu_alta_listadoplanetstarwars.ui.locals.LocalPlanetPadding
import com.example.menu_alta_listadoplanetstarwars.ui.theme.Menu_Alta_ListadoPlanetStarWarsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Menu_Alta_ListadoPlanetStarWarsTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                var expanded by remember { mutableStateOf(false) }

                CompositionLocalProvider(LocalPlanetPadding provides 16.dp) {

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                title = { Text("Planetas Star Wars") },
                                actions = {
                                    IconButton(onClick = { navController.navigate(Routes.ADD) }) {
                                        Icon(imageVector = Icons.Default.Add, contentDescription = "Ir a Añadir")
                                    }
                                    IconButton(onClick = { navController.navigate(Routes.LIST) }) {
                                        Icon(imageVector = Icons.Default.List, contentDescription = "Ir a Listar")
                                    }

                                    Box {
                                        IconButton(onClick = { expanded = true }) {
                                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Abrir Menú")
                                        }
                                        DropdownMenu(
                                            expanded = expanded,
                                            onDismissRequest = { expanded = false }
                                        ) {
                                            DropdownMenuItem(
                                                text = { Text("Sobre Nosotros") },
                                                onClick = {
                                                    expanded = false
                                                    navController.navigate(Routes.ABOUT)
                                                }
                                            )
                                        }
                                    }
                                }
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(onClick = { navController.navigate(Routes.ADD) }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir Nuevo")
                            }
                        }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            NavHostScreen(navHostController = navController, snackbarHostState = snackbarHostState )
                        }
                    }
                }
            }
        }
    }
}