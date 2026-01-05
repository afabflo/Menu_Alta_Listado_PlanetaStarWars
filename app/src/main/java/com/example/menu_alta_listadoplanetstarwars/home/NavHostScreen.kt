package com.example.menu_alta_listadoplanetstarwars.home

import android.R.attr.animation
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.menu_alta_listadoplanetstarwars.screens.AboutUsScreen
import com.example.menu_alta_listadoplanetstarwars.screens.AñadirPlaneta
import com.example.menu_alta_listadoplanetstarwars.screens.EditarPlaneta
import com.example.menu_alta_listadoplanetstarwars.screens.ListPlanets
import com.example.menu_alta_listadoplanetstarwars.viewModel.AñadirViewModel
import com.example.menu_alta_listadoplanetstarwars.viewModel.EditarViewModel
import com.example.menu_alta_listadoplanetstarwars.viewModel.ListadoViewModel

object Routes{
    const val LIST = "list"
            const  val ADD = "add"
    const val ABOUT = "about"
    const val EDIT = "edit"
}

@Composable
    fun NavHostScreen(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
    ) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.LIST,

        ){
        composable(Routes.LIST) {
            val viewModel: ListadoViewModel = hiltViewModel()
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(durationMillis = 500))
            ) {
                ListPlanets(
                    viewModel = viewModel,
                    navController = navHostController,
                    snackbarHostState = snackbarHostState
                )
            }
        }
        composable(Routes.ADD) {
            val viewModel: AñadirViewModel = hiltViewModel()
            AnimatedVisibility(
                visible = true,
                enter = scaleIn(initialScale = 0.5f, animationSpec = tween(600)) + fadeIn()
            ) {
                AñadirPlaneta(
                    viewModel = viewModel,
                    onBack = { navHostController.popBackStack() },
                    modifier = Modifier
                )
            }
        }
        composable(Routes.ABOUT) {
            AnimatedVisibility(
                visible = true,
                enter = scaleIn(initialScale = 0.1f, animationSpec = tween(1000)) + fadeIn()
            ) {
                AboutUsScreen(Modifier)
            }
        }
        composable(Routes.EDIT){
        val viewModel : EditarViewModel = hiltViewModel()
            EditarPlaneta(viewModel,navHostController,snackbarHostState)
        }

    }
    }
