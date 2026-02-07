package com.example.menu_alta_listadoplanetstarwars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBar
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBarState
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseFabState // IMPORTANTE: Asegúrate de tener este import
import com.example.menu_alta_listadoplanetstarwars.home.NavHostScreen
import com.example.menu_alta_listadoplanetstarwars.home.Routes
import com.example.menu_alta_listadoplanetstarwars.ui.locals.LocalPlanetPadding
import com.example.menu_alta_listadoplanetstarwars.ui.theme.Menu_Alta_ListadoPlanetStarWarsTheme
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Menu_Alta_ListadoPlanetStarWarsTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val menuIcon = painterResource(id = R.drawable.ic_launcher)
                val snackbarHostState = remember { SnackbarHostState() }

                // 1. ESTADO PARA LA TOPBAR
                var topBarState by remember { mutableStateOf(BaseTopAppBarState(
                    title = "Planetas Star Wars",
                    iconUpAction = menuIcon,
                    upAction = { scope.launch { drawerState.open() } },
                    actions = emptyList()
                )) }

                // 2. NUEVO: ESTADO PARA EL FAB (Botón Flotante)
                // Por defecto lo iniciamos invisible o con la acción de ir a "Añadir"
                var fabState by remember { mutableStateOf(BaseFabState(
                    icon = null, // Se puede usar un icono por defecto si quieres
                    isVisible = false,
                    action = { navController.navigate(Routes.ADD) }
                )) }

                CompositionLocalProvider(LocalPlanetPadding provides 16.dp) {
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

                                NavigationDrawerItem(
                                    label = { Text("Listado Planetas") },
                                    selected =  currentRoute?.startsWith("list") == true || currentRoute == Routes.PLANETS_GRAPH,
                                    onClick = {
                                        navController.navigate(Routes.PLANETS_GRAPH) {
                                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                        scope.launch { drawerState.close() }
                                    }
                                )

                                NavigationDrawerItem(
                                    label = { Text("Listado Películas") },
                                    selected = currentRoute == Routes.FILMS,
                                    onClick = {
                                        navController.navigate(Routes.FILMS) { popUpTo(Routes.LIST) }
                                        scope.launch { drawerState.close() }
                                    }
                                )

                                NavigationDrawerItem(
                                    label = { Text("Listado Personajes") },
                                    selected = currentRoute == Routes.PEOPLE,
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
                            // 3. FAB DINÁMICO: Ahora reacciona al fabState
                            floatingActionButton = {
                                if (fabState.isVisible) {
                                    FloatingActionButton(
                                        onClick = { fabState.action() },
                                        containerColor = colorWars,
                                        contentColor = Color.Black
                                    ) {
                                        // Si el estado tiene un icono (Int), lo dibujamos
                                        fabState.icon?.let { iconRes ->
                                            Icon(
                                                painter = painterResource(id = iconRes),
                                                contentDescription = "Acción FAB"
                                            )
                                        } ?: Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir")
                                    }
                                }
                            }
                        ) { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) {
                                // 4. PASAMOS AMBOS UPDATERS AL NAVHOST
                                NavHostScreen(
                                    navHostController = navController,
                                    snackbarHostState = snackbarHostState,
                                    onUpdateTopBar = { newState ->
                                        // Mantenemos la lógica de que el botón de arriba abra el drawer
                                        topBarState = newState.copy(
                                            upAction = {
                                                if (newState.title == "Planetas Star Wars") {
                                                    scope.launch { drawerState.open() }
                                                } else {
                                                    newState.upAction()
                                                }
                                            }
                                        )
                                    },
                                    onUpdateFab = { nuevoFabState ->
                                        fabState = nuevoFabState
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