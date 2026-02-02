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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.menu_alta_listadoplanetstarwars.R
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBarState
import com.example.menu_alta_listadoplanetstarwars.ui.components.CampoTextoPlaneta
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import com.example.menu_alta_listadoplanetstarwars.viewModel.EditarViewModel

@Composable
fun EditarPlaneta(
    viewModel: EditarViewModel,
    navController: NavController,
    snackbarHostState: SnackbarHostState
    ,onUpdateTopBar : (BaseTopAppBarState) -> Unit
) {
    val scrollState = rememberScrollState()
    val menuIcon = painterResource(id = R.drawable.ic_launcher)
    LaunchedEffect(Unit) {
        onUpdateTopBar(
            BaseTopAppBarState(title = "Editar Planeta",
                iconUpAction = menuIcon,
                upAction = {navController.popBackStack()})
        )
    }
    // No necesitamos scope aquí si solo vamos a navegar
    // val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        // HeaderBox() // Asegúrate de que esto existe o coméntalo si da error
        Text("Editando: ${viewModel.name}", color = colorWars)

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
                    // CORRECCIÓN: Quitamos el scope.launch y el snackbar bloqueante.
                    // Navegamos inmediatamente hacia atrás.
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorWars)
        ) {
            Text("Guardar cambios", color = Color.Black)
        }
    }
}