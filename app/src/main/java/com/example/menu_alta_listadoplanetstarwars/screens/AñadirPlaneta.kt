package com.example.menu_alta_listadoplanetstarwars.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.menu_alta_listadoplanetstarwars.R
import com.example.menu_alta_listadoplanetstarwars.ui.components.CampoTextoPlaneta
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import com.example.menu_alta_listadoplanetstarwars.viewModel.AñadirViewModel

@Composable
fun  AñadirPlaneta(modifier: Modifier,
       viewModel: AñadirViewModel,onBack:() -> Unit)
{
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize().
            verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HeaderBox()

        CampoTextoPlaneta(viewModel.name, { viewModel.name = it }, "Nombre")
        CampoTextoPlaneta(viewModel.rotationPeriod, { viewModel.rotationPeriod = it }, "Periodo de rotacion")
        CampoTextoPlaneta(viewModel.orbitalPeriod, { viewModel.orbitalPeriod = it }, "Periodo orbital")
        CampoTextoPlaneta(viewModel.diameter, { viewModel.diameter = it }, "Diametro")
        CampoTextoPlaneta(viewModel.climate, { viewModel.climate = it }, "Clima")
        CampoTextoPlaneta(viewModel.gravity, { viewModel.gravity = it }, "Gravedad")
        CampoTextoPlaneta(viewModel.terrain, { viewModel.terrain = it }, "Terreno")
        CampoTextoPlaneta(viewModel.surfaceWater, { viewModel.surfaceWater = it }, "Agua superficial")
        CampoTextoPlaneta(viewModel.population, { viewModel.population = it }, "Poblacion")
        Button(onClick = {
            viewModel.insertarPlaneta { onBack() }
        },
            modifier= Modifier.fillMaxWidth().padding(top=8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorWars)
        ) {
            Text(text = "Añadir Planeta", color = colorWars)
        }
    }
}

@Composable
fun HeaderBox() {
   Box(
       modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
   ){
       Image(
           painter = painterResource(R.drawable.planetaswars),
           modifier = Modifier.fillMaxWidth().height(180.dp),
           contentDescription = stringResource(R.string.planetaFoto),
           contentScale = ContentScale.Crop
       )
       Box(
           modifier = Modifier.background(Color(0xAA000000)).matchParentSize()
       )
       Text(
           text = stringResource(R.string.starwarsTitleFoto),
           fontSize = 12.sp,
           color = colorWars,
           fontWeight = FontWeight.Bold,
           modifier = Modifier.align(Alignment.BottomCenter)
       )

   }
}


@Preview(showBackground = true)
@Composable
fun AñadirView(){
    //AñadirPlaneta(modifier = Modifier)
}