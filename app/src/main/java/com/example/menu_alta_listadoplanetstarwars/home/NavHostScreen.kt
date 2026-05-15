// home/NavHostScreen.kt
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
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseFabState
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBarState
import com.example.menu_alta_listadoplanetstarwars.screens.AboutUsScreen
import com.example.menu_alta_listadoplanetstarwars.screens.AñadirPelicula
import com.example.menu_alta_listadoplanetstarwars.screens.AñadirPersona
import com.example.menu_alta_listadoplanetstarwars.screens.AñadirPlaneta
import com.example.menu_alta_listadoplanetstarwars.screens.EditarPelicula
import com.example.menu_alta_listadoplanetstarwars.screens.EditarPersona
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
    const val EDIT = "edit"

    const val FILMS = "films"
    const val PEOPLE = "people"
    const val ABOUT = "about"

    const val ADD_PERSON = "add_person"
    const val EDIT_PERSON = "edit_person/{personId}"

    fun editPerson(personId: Int): String {
        return "edit_person/$personId"
    }

    const val ADD_FILM = "add_film"
    const val EDIT_FILM = "edit_film/{filmId}"

    fun editFilm(filmId: Int): String {
        return "edit_film/$filmId"
    }
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

            onUpdateTopBar(
                BaseTopAppBarState(
                    title = "Películas Star Wars",
                    upAction = { navHostController.popBackStack() }
                )
            )

            onUpdateFab(
                BaseFabState(
                    isVisible = true,
                    action = {
                        navHostController.navigate(Routes.ADD_FILM)
                    }
                )
            )

            ListFilms(
                viewModel = viewModel,
                navController = navHostController,
                snackbarHostState = snackbarHostState,
                onUpdateTopBar = onUpdateTopBar
            )
        }

        composable(Routes.PEOPLE) {
            val viewModel: PersonViewModel = hiltViewModel()

            onUpdateTopBar(
                BaseTopAppBarState(
                    title = "Personajes Star Wars",
                    upAction = { navHostController.popBackStack() }
                )
            )

            onUpdateFab(
                BaseFabState(
                    isVisible = true,
                    action = {
                        navHostController.navigate(Routes.ADD_PERSON)
                    }
                )
            )

            ListPeople(
                viewModel = viewModel,
                navController = navHostController,
                snackbarHostState = snackbarHostState
            )
        }

        composable(Routes.ADD_PERSON) {
            AñadirPersona(
                navController = navHostController
            )
        }

        composable(Routes.EDIT_PERSON) { backStackEntry ->
            val personId = backStackEntry.arguments
                ?.getString("personId")
                ?.toIntOrNull() ?: 0

            EditarPersona(
                navController = navHostController,
                personId = personId
            )
        }

        composable(Routes.ADD_FILM) {
            AñadirPelicula(
                navController = navHostController
            )
        }

        composable(Routes.EDIT_FILM) { backStackEntry ->
            val filmId = backStackEntry.arguments
                ?.getString("filmId")
                ?.toIntOrNull() ?: 0

            EditarPelicula(
                navController = navHostController,
                filmId = filmId
            )
        }

        composable(Routes.ABOUT) {
            onUpdateFab(
                BaseFabState(
                    isVisible = false,
                    action = {}
                )
            )

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

            onUpdateFab(
                BaseFabState(
                    isVisible = true,
                    action = {
                        navHostController.navigate(Routes.ADD)
                    }
                )
            )

            ListPlanets(
                viewModel = viewModel,
                navController = navHostController,
                snackbarHostState = snackbarHostState,
                onUpdateTopBar = onUpdateTopBar,
                onUpdateFab = onUpdateFab
            )
        }

        composable(Routes.ADD) {
            onUpdateFab(
                BaseFabState(
                    isVisible = false,
                    action = {}
                )
            )

            val viewModel: AñadirViewModel = hiltViewModel()

            AñadirPlaneta(
                modifier = Modifier,
                navHostController = navHostController,
                onUpdateTopBar = onUpdateTopBar,
                onUpdateFab = onUpdateFab,
                viewModel = viewModel,
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(Routes.EDIT) {
            onUpdateFab(
                BaseFabState(
                    isVisible = false,
                    action = {}
                )
            )

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