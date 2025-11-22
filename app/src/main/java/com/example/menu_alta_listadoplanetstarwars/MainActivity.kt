package com.example.menu_alta_listadoplanetstarwars

import android.annotation.SuppressLint
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.menu_alta_listadoplanetstarwars.ui.locals.LocalPlanetPadding
import com.example.menu_alta_listadoplanetstarwars.ui.screen.AboutUsScreen
import com.example.menu_alta_listadoplanetstarwars.ui.screen.AñadirPlaneta
import com.example.menu_alta_listadoplanetstarwars.ui.screen.ListPlanets
import com.example.menu_alta_listadoplanetstarwars.ui.theme.Menu_Alta_ListadoPlanetStarWarsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Menu_Alta_ListadoPlanetStarWarsTheme {

                var current by remember { mutableStateOf("add") }
                var expanded by remember { mutableStateOf(false) }
                var name by remember { mutableStateOf("") }
                var rotationPeriod by remember { mutableStateOf("") }
                var orbitalPeriod by remember { mutableStateOf("") }
                var diameter by remember { mutableStateOf("") }
                var climate by remember { mutableStateOf("") }
                var gravity by remember { mutableStateOf("") }
                var terrain by remember { mutableStateOf("") }
                var surfaceWater by remember { mutableStateOf("") }
                var population by remember { mutableStateOf("") }
                CompositionLocalProvider(
                    LocalPlanetPadding provides 16.dp
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                title = { Text("Planetas") },
                                actions = {
                                    IconButton(
                                        onClick = { current = "add" }
                                    ) {
                                        Icon(Icons.Default.Add, contentDescription = "Añadir")
                                    }
                                    IconButton(
                                        onClick = { current = "listar" }
                                    ) {
                                        Icon(Icons.Default.List, contentDescription = "Listar")
                                    }
                                    Box {
                                        IconButton(onClick = { expanded = true }) {
                                            Icon(
                                                Icons.Default.MoreVert,
                                                contentDescription = "Menú"
                                            )
                                        }

                                        DropdownMenu(
                                            expanded = expanded,
                                            onDismissRequest = { expanded = false }
                                        ) {
                                            DropdownMenuItem(
                                                text = { Text("Sobre Nosotros") },
                                                onClick = {
                                                    expanded = false
                                                    current = "about"
                                                }
                                            )
                                        }
                                    }

                                }
                            )
                        }
                    ) { innerPadding ->

                        when (current) {
                            "add" -> AñadirPlaneta(
                                modifier = Modifier.padding(innerPadding),
                                name = name,
                                onNameChange = { name = it },
                                rotationPeriod = rotationPeriod,
                                onRotationChange = { rotationPeriod = it },
                                orbitalPeriod = orbitalPeriod,
                                onOrbitalChange = { orbitalPeriod = it },
                                diameter = diameter,
                                onDiameterChange = { diameter = it },
                                climate = climate,
                                onClimateChange = { climate = it },
                                gravity = gravity,
                                onGravityChange = { gravity = it },
                                terrain = terrain,
                                onTerrainChange = { terrain = it },
                                surfaceWater = surfaceWater,
                                onSurfaceWaterChange = { surfaceWater = it },
                                population = population,
                                onPopulationChange = { population = it }
                            )

                            "listar" -> ListPlanets(Modifier.padding(innerPadding))
                            "about" -> AboutUsScreen(Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }
}