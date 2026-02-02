package com.example.menu_alta_listadoplanetstarwars

import android.R.attr.type
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.composables.deathstaricon
import com.composables.orbitalSystemSvgrepoCom
import com.composables.stromTrooper
import com.composables.terrainicon
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBar
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBarState
import com.example.menu_alta_listadoplanetstarwars.home.NavHostScreen
import com.example.menu_alta_listadoplanetstarwars.home.Routes
import com.example.menu_alta_listadoplanetstarwars.ui.locals.LocalPlanetPadding
import com.example.menu_alta_listadoplanetstarwars.ui.theme.Menu_Alta_ListadoPlanetStarWarsTheme
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Menu_Alta_ListadoPlanetStarWarsTheme {
                //  Estados de Navegación y Drawer
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                //  ruta actual para el selected del Drawer
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val menuIcon = painterResource(id = R.drawable.ic_launcher)
                val snackbarHostState = remember { SnackbarHostState() }

                var topBarState by remember { mutableStateOf(BaseTopAppBarState(
                    title = "Planetas Star Wars",
                    iconUpAction = menuIcon,
                    //Modificaciones a  la acción para que abra el Drawer
                    upAction = { scope.launch { drawerState.open() } },
                    actions = emptyList()
                )) }

                CompositionLocalProvider(LocalPlanetPadding provides 16.dp) {
                    // Envolver
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet(modifier = Modifier.width(300.dp)) {
                                Text(
                                    text = "Menú Star Wars",
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.titleLarge

                                )

                                HorizontalDivider()

                                /*NavigationDrawerItem(
                                    label = { Text("Listado Especies") },
                                    selected = currentRoute == Routes.LIST,
                                    onClick = {
                                        navController.navigate(Routes.LIST) { popUpTo(Routes.LIST) { inclusive = true } }
                                        scope.launch { drawerState.close() }
                                    }
                                )
                                */


                                NavigationDrawerItem(
                                    label = { Text("Listado Planetas") },
                                    selected =  currentRoute?.startsWith("list") == true || currentRoute == Routes.PLANETS_GRAPH,
                                   /* icon = {
                                        Icon(imageVector = terrainicon,
                                            contentDescription = null,
                                            tint = Color.White, modifier = Modifier.padding(2.dp))
                                    },*/
                                    onClick = {
                                        navController.navigate(Routes.PLANETS_GRAPH) {
                                            popUpTo(navController.graph.startDestinationId)
                                            {
                                            saveState = true
                                        }
                                            launchSingleTop = true
                                            restoreState = true

                                        }
                                        scope.launch { drawerState.close() }
                                    }
                                )

                                NavigationDrawerItem(
                                    label = { Text("Listado Películas") },
                                    selected = currentRoute == Routes.FILMS,
                                    /*icon = {
                                        Icon(imageVector = deathstaricon,
                                            contentDescription = null,

                                            tint = colorWars,
                                            modifier = Modifier.padding(2.dp)

                                        )

                                    },*/
                                    onClick = {
                                        navController.navigate(Routes.FILMS) { popUpTo(Routes.LIST) }
                                        scope.launch { drawerState.close() }
                                    }
                                )

                                NavigationDrawerItem(
                                    label = { Text("Listado Personajes") },
                                    selected = currentRoute == Routes.PEOPLE,
                                  /*  icon = {
                                        Icon(imageVector = stromTrooper,
                                            contentDescription = null,
                                            modifier = Modifier.padding(2.dp),
                                            tint = Color.Red)
                                    },
                                    */

                                    onClick = {
                                        navController.navigate(Routes.PEOPLE) { popUpTo(Routes.LIST) }
                                        scope.launch { drawerState.close() }
                                    }
                                )

                                NavigationDrawerItem(
                                    label = { Text("Acerca de") },
                                    selected = currentRoute == Routes.ABOUT,
                                    onClick = {
                                        navController.navigate(Routes.ABOUT) { popUpTo(Routes.LIST) }
                                        scope.launch { drawerState.close() }
                                    }
                                )
                            }
                        }
                    ) {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            topBar = {
                                BaseTopAppBar(state = topBarState)
                            },
                            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                            floatingActionButton = {
                                FloatingActionButton(onClick = { navController.navigate(Routes.ADD) }) {
                                    Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir Nuevo")
                                }
                            }
                        ) { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) {
                                NavHostScreen(
                                    navHostController = navController,
                                    snackbarHostState = snackbarHostState,
                                    onUpdateTopBar = { newState ->
                                        topBarState = newState.copy(
                                            upAction = { scope.launch { drawerState.open() } }
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}