package com.example.menu_alta_listadoplanetstarwars.ui.screen

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun  AñadirPlaneta(modifier: Modifier,
                   name: String,
                   onNameChange: (String) -> Unit,
                   rotationPeriod: String,
                   onRotationChange: (String) -> Unit,
                   orbitalPeriod: String,
                   onOrbitalChange: (String) -> Unit,
                   diameter: String,
                   onDiameterChange: (String) -> Unit,
                   climate: String,
                   onClimateChange: (String) -> Unit,
                   gravity: String,
                   onGravityChange: (String) -> Unit,
                   terrain: String,
                   onTerrainChange: (String) -> Unit,
                   surfaceWater: String,
                   onSurfaceWaterChange: (String) -> Unit,
                   population: String,
                   onPopulationChange: (String) -> Unit) {
    /*var name by remember { mutableStateOf("") }
    var rotationPeriod by remember { mutableStateOf("") }
    var orbitalPeriod by remember { mutableStateOf("") }
    var diameter by remember { mutableStateOf("") }
    var climate by remember { mutableStateOf("") }
    var gravity by remember { mutableStateOf("") }
    var terrain by remember { mutableStateOf("") }
    var surfaceWater by remember { mutableStateOf("") }
    var population by remember { mutableStateOf("") }
    */

    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize().verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HeaderBox()
        /*
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = rotationPeriod,
            onValueChange = onRotationChange,
            label = { Text("Rotation Period") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = orbitalPeriod,
            onValueChange = onOrbitalChange,
            label = { Text("Orbital Period") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = diameter,
            onValueChange = onDiameterChange,
            label = { Text("Diameter") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = climate,
            onValueChange = onClimateChange,
            label = { Text("Climate") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = gravity,
            onValueChange = onGravityChange,
            label = { Text("Gravity") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = terrain,
            onValueChange = onTerrainChange,
            label = { Text("Terrain") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = surfaceWater,
            onValueChange = onSurfaceWaterChange,
            label = { Text("Surface Water") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = population,
            onValueChange = onPopulationChange,
            label = { Text("Population") },
            modifier = Modifier.fillMaxWidth()
        )
        */
        CampoTextoPlaneta(name, onNameChange, "Nombre")
        CampoTextoPlaneta(rotationPeriod, onRotationChange, "Período de rotación")
        CampoTextoPlaneta(orbitalPeriod, onOrbitalChange, "Período orbital")
        CampoTextoPlaneta(diameter, onDiameterChange, "Diámetro")
        CampoTextoPlaneta(climate, onClimateChange, "Clima")
        CampoTextoPlaneta(gravity, onGravityChange, "Gravedad")
        CampoTextoPlaneta(terrain, onTerrainChange, "Terreno")
        CampoTextoPlaneta(surfaceWater, onSurfaceWaterChange, "Agua superficial")
        CampoTextoPlaneta(population, onPopulationChange, "Población")
    }}

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