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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBarState
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseFabState
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
                val snackbarHostState = remember { SnackbarHostState() }

                var topBarState by remember { mutableStateOf(BaseTopAppBarState(
                    title = "Planetas Star Wars",
                    iconUpAction = null,
                    upAction = { scope.launch { drawerState.open() } },
                    actions = emptyList()
                )) }

                var fabState by remember { mutableStateOf(BaseFabState(
                    icon = null,
                    isVisible = false,
                    action = { navController.navigate(Routes.ADD) }
                )) }

                androidx.activity.compose.BackHandler(enabled = drawerState.isOpen) {
                    scope.launch { drawerState.close() }
                }

                CompositionLocalProvider(LocalPlanetPadding provides 16.dp) {
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet(modifier = Modifier.width(300.dp)) {
                                Text(text = "Menú Galáctico", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                                HorizontalDivider()
                                NavigationDrawerItem(
                                    label = { Text("Planetas") },
                                    selected = currentRoute == Routes.LIST || currentRoute == Routes.PLANETS_GRAPH,
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
                                    label = { Text("Películas") },
                                    selected = currentRoute == Routes.FILMS,
                                    onClick = {
                                        navController.navigate(Routes.FILMS)
                                        scope.launch { drawerState.close() }
                                    }
                                )
                                NavigationDrawerItem(
                                    label = { Text("Personajes") },
                                    selected = currentRoute == Routes.PEOPLE,
                                    onClick = {
                                        navController.navigate(Routes.PEOPLE)
                                        scope.launch { drawerState.close() }
                                    }
                                )
                                NavigationDrawerItem(
                                    label = { Text("Sobre nosotros") },
                                    selected = currentRoute == Routes.ABOUT,
                                    onClick = {
                                        navController.navigate(Routes.ABOUT)
                                        scope.launch { drawerState.close() }
                                    }
                                )
                            }
                        }
                    ) {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            topBar = {
                                CenterAlignedTopAppBar(
                                    title = { Text(topBarState.title) },
                                    navigationIcon = {
                                        IconButton(onClick = { topBarState.upAction() }) {
                                            if (topBarState.title == "Planetas Star Wars" || currentRoute == Routes.LIST) {
                                                Icon(imageVector = Icons.Default.Menu, contentDescription = null, tint = Color.White)
                                            } else {
                                                Icon(painter = painterResource(id = android.R.drawable.ic_menu_revert), contentDescription = null, tint = colorWars)
                                            }
                                        }
                                    },
                                    actions = {
                                        var mExpanded by remember { mutableStateOf(false) }
                                        IconButton(onClick = { mExpanded = true }) {
                                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null, tint = colorWars)
                                        }
                                        DropdownMenu(expanded = mExpanded, onDismissRequest = { mExpanded = false }) {
                                            DropdownMenuItem(
                                                text = { Text("Sobre nosotros") },
                                                onClick = {
                                                    mExpanded = false
                                                    navController.navigate(Routes.ABOUT)
                                                }
                                            )
                                        }
                                    },
                                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Black, titleContentColor = colorWars)
                                )
                            },
                            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                            floatingActionButton = {
                                if (fabState.isVisible) {
                                    FloatingActionButton(onClick = { fabState.action() }, containerColor = colorWars, contentColor = Color.Black) {
                                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                                    }
                                }
                            }
                        ) { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) {
                                NavHostScreen(
                                    navHostController = navController,
                                    snackbarHostState = snackbarHostState,
                                    onUpdateTopBar = { newState ->
                                        topBarState = newState.copy(
                                            upAction = {
                                                if (navController.previousBackStackEntry == null || currentRoute == Routes.LIST) {
                                                    scope.launch { drawerState.open() }
                                                } else {
                                                    navController.popBackStack()
                                                }
                                            }
                                        )
                                    },
                                    onUpdateFab = { nuevoFabState -> fabState = nuevoFabState }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}