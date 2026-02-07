package com.example.menu_alta_listadoplanetstarwars.home

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseFabState
import com.example.menu_alta_listadoplanetstarwars.screens.AboutUsScreen
import com.example.menu_alta_listadoplanetstarwars.screens.AñadirPlaneta
import com.example.menu_alta_listadoplanetstarwars.screens.EditarPlaneta
import com.example.menu_alta_listadoplanetstarwars.screens.ListFilms
import com.example.menu_alta_listadoplanetstarwars.screens.ListPeople
import com.example.menu_alta_listadoplanetstarwars.screens.ListPlanets
import com.example.menu_alta_listadoplanetstarwars.viewModel.AñadirViewModel
import com.example.menu_alta_listadoplanetstarwars.viewModel.EditarViewModel
import com.example.menu_alta_listadoplanetstarwars.viewModel.FilmViewModel
import com.example.menu_alta_listadoplanetstarwars.viewModel.ListadoViewModel
import com.example.menu_alta_listadoplanetstarwars.viewModel.PersonViewModel

object Routes {
    const val PLANETS_GRAPH = "planets_graph"
    const val LIST = "list"
    const val ADD = "add"
    const val ABOUT = "about"
    const val EDIT = "edit"
    const val FILMS = "films"
    const val PEOPLE = "people"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavHostScreen(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    onUpdateTopBar: (BaseTopAppBarState) -> Unit,
    onUpdateFab: (BaseFabState) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.PLANETS_GRAPH
    ) {
        planetsGraph(
            navHostController = navHostController,
            snackbarHostState = snackbarHostState,
            onUpdateTopBar = onUpdateTopBar,
            onUpdateFab = onUpdateFab
        )

        composable(Routes.FILMS) {
            val viewModel: FilmViewModel = hiltViewModel()
            ListFilms(
                viewModel = viewModel,
                navController = navHostController,
                snackbarHostState = snackbarHostState,
                onUpdateTopBar = onUpdateTopBar,
            )
        }

        composable(Routes.PEOPLE) {
            val viewModel: PersonViewModel = hiltViewModel()
            ListPeople(
                viewModel = viewModel,
                navController = navHostController,
                snackbarHostState = snackbarHostState,
            )
        }
//a
        composable(Routes.ABOUT) {
            AboutUsScreen(
                navController = navHostController,
                onUpdateTopBar = onUpdateTopBar
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.planetsGraph(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    onUpdateTopBar: (BaseTopAppBarState) -> Unit,
    onUpdateFab: (BaseFabState) -> Unit
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
                onUpdateTopBar = onUpdateTopBar,
                onUpdateFab = onUpdateFab
            )
        }

        composable(Routes.ADD) {
            val viewModel: AñadirViewModel = hiltViewModel()
            AñadirPlaneta(
                modifier = Modifier,
                navHostController = navHostController,
                onUpdateTopBar = onUpdateTopBar,
                onUpdateFab = onUpdateFab,
                viewModel = viewModel,
                onBack = { navHostController.popBackStack() }
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