package com.example.menu_alta_listadoplanetstarwars.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.menu_alta_listadoplanetstarwars.ui.components.CampoTextoPlaneta
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import com.example.menu_alta_listadoplanetstarwars.viewModel.AñadirViewModel
import com.example.menu_alta_listadoplanetstarwars.viewModel.EditarViewModel
import kotlinx.coroutines.launch

@Composable
fun EditarPlaneta(
    viewModel: EditarViewModel,
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {
val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        HeaderBox()
        Text("Editando${viewModel.name}", color = colorWars, )
        CampoTextoPlaneta(viewModel.name, { viewModel.name = it }, "Nombre")
        CampoTextoPlaneta(viewModel.rotationPeriod, { viewModel.rotationPeriod = it }, "Periodo de rotacion")
        CampoTextoPlaneta(viewModel.orbitalPeriod, { viewModel.orbitalPeriod = it }, "Periodo orbital")
        CampoTextoPlaneta(viewModel.diameter, { viewModel.diameter = it }, "Diametro")
        CampoTextoPlaneta(viewModel.climate, { viewModel.climate = it }, "Clima")
        CampoTextoPlaneta(viewModel.gravity, { viewModel.gravity = it }, "Gravedad")
        CampoTextoPlaneta(viewModel.terrain, { viewModel.terrain = it }, "Terreno")
        CampoTextoPlaneta(viewModel.surfaceWater, { viewModel.surfaceWater = it }, "Agua superficial")
        CampoTextoPlaneta(viewModel.population, { viewModel.population = it }, "Poblacion")
        Button(
            onClick = {
                viewModel.actualizarPlaneta {
                    scope.launch {
                    snackbarHostState.showSnackbar("Planeta Actualizado correctamente")
                        navController.popBackStack()
                }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorWars)
        ) {
            Text("Guardar cambios", color = Color.Black)
        }
    }

}