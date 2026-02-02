package com.example.menu_alta_listadoplanetstarwars.home

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBarState
import com.example.menu_alta_listadoplanetstarwars.screens.AboutUsScreen
import com.example.menu_alta_listadoplanetstarwars.screens.AñadirPlaneta
import com.example.menu_alta_listadoplanetstarwars.screens.EditarPlaneta
import com.example.menu_alta_listadoplanetstarwars.screens.ListPlanets
import com.example.menu_alta_listadoplanetstarwars.viewModel.AñadirViewModel
import com.example.menu_alta_listadoplanetstarwars.viewModel.EditarViewModel
import com.example.menu_alta_listadoplanetstarwars.viewModel.ListadoViewModel

object Routes {
    const val PLANETS_GRAPH = "planets_graph"
    const val LIST = "list"
    const val PLANETS = "list"
    const val ADD = "add"
    const val ABOUT = "about"
    const val EDIT = "edit"
    const val FILMS = "films"
    const val SPECIES = "species"
    const val PEOPLE = "people"
}

@Composable
fun NavHostScreen(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    onUpdateTopBar : (BaseTopAppBarState) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.PLANETS_GRAPH
    ) {
        composable(Routes.LIST) {
            val viewModel: ListadoViewModel = hiltViewModel()
            ListPlanets(
                viewModel = viewModel,
                navController = navHostController,
                snackbarHostState = snackbarHostState,
                onUpdateTopBar =  onUpdateTopBar
            )
        }
        planetsGraph(
            navHostController = navHostController,
            snackbarHostState = snackbarHostState,
            onUpdateTopBar = onUpdateTopBar
        )

        // --- LISTA PELÍCULAS ---
        composable(Routes.FILMS) {
            val viewModel: ListadoViewModel = hiltViewModel()
            ListPlanets(
                viewModel = viewModel,
                navController = navHostController,
                snackbarHostState = snackbarHostState,
                onUpdateTopBar = onUpdateTopBar
            )
        }

        composable(Routes.PEOPLE) {
            val viewModel: ListadoViewModel = hiltViewModel()
            ListPlanets(
                viewModel = viewModel,
                navController = navHostController,
                snackbarHostState = snackbarHostState,
                onUpdateTopBar = onUpdateTopBar
            )
        }

        composable(Routes.SPECIES) {
            val viewModel: ListadoViewModel = hiltViewModel()
            ListPlanets(
                viewModel = viewModel,
                navController = navHostController,
                snackbarHostState = snackbarHostState,
                onUpdateTopBar = onUpdateTopBar
            )
        }

        composable(Routes.ADD) {
            val viewModel: AñadirViewModel = hiltViewModel()
            AñadirPlaneta(
                viewModel = viewModel,
                onBack = { navHostController.popBackStack() },
                modifier = Modifier,
                navHostController = navHostController,
                onUpdateTopBar = onUpdateTopBar
            )
        }

        // --- ABOUT ---
        composable(Routes.ABOUT) {
            AboutUsScreen(
                modifier = Modifier,
                onUpdateTopBar = onUpdateTopBar,
                navController = navHostController
            )
        }

        // --- EDITAR ---
        composable(Routes.EDIT) {
            val viewModel: EditarViewModel = hiltViewModel()
            EditarPlaneta(
                viewModel = viewModel,
                navController = navHostController,
                snackbarHostState = snackbarHostState,
                onUpdateTopBar = onUpdateTopBar
            )
        }
    }
}

fun NavGraphBuilder.planetsGraph(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    onUpdateTopBar: (BaseTopAppBarState) -> Unit
) {
    navigation(
        startDestination = Routes.LIST,

        route = Routes.PLANETS_GRAPH
    ) {
        composable(Routes.LIST) {
            val viewModel: ListadoViewModel = hiltViewModel()
            ListPlanets(
                viewModel = viewModel,
                navController = navHostController,
                snackbarHostState = snackbarHostState,
                onUpdateTopBar = onUpdateTopBar
            )
        }

        composable(Routes.ADD) {
            val viewModel: AñadirViewModel = hiltViewModel()
            AñadirPlaneta(
                viewModel = viewModel,
                onBack = { navHostController.popBackStack() },
                modifier = Modifier,
                navHostController = navHostController,
                onUpdateTopBar = onUpdateTopBar
            )
        }

        composable(Routes.EDIT) {
            val viewModel: EditarViewModel = hiltViewModel()
            EditarPlaneta(
                viewModel = viewModel,
                navController = navHostController,
                snackbarHostState = snackbarHostState,
                onUpdateTopBar = onUpdateTopBar
            )
        }
    }
}