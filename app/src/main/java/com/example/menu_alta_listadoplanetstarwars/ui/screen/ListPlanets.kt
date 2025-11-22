package com.example.menu_alta_listadoplanetstarwars.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpace
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.TextStyle
import com.example.menu_alta_listadoplanetstarwars.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.deathstaricon
import com.example.menu_alta_listadoplanetstarwars.data.repository.ListaPlanetas.planetas
import com.example.menu_alta_listadoplanetstarwars.ui.locals.LocalPlanetPadding
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPlanets(
modifier: Modifier
) {
    var lista = planetas;
    LazyColumn(
        modifier = modifier.fillMaxSize()){
        items(planetas){
            item ->
            Card (
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 12.dp),
                shape = CardDefaults.shape,
                colors = CardDefaults.cardColors(containerColor = Color.Black),
                onClick = {}
            ){
                /*
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                ){
                    Icon(
                        imageVector = deathstaricon,
                        contentDescription = "Estrella de la muerte",
                        tint = Color.White
                    )
                    */

                Column(
                    modifier = Modifier.padding(LocalPlanetPadding.current), verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = deathstaricon,
                        contentDescription = "Estrella de la muerte",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(text = "${item.name}", style = TextStyle(color = colorWars))
                    Text(text = "Clima: ${item.climate}", style = TextStyle(color = Color.White))
                    Text(text = "Populaton: ${item.population}", style = TextStyle(color = Color.White))
                    Text(text = "Terrain: ${item.terrain}", style = TextStyle(color = Color.White))
                    Text(text = "Residentes:${item.residents.joinToString(", ")}", style = TextStyle(color = Color.White))
                    Text(text = "Films: ${item.films}", style = TextStyle(color = Color.White))
                    Text(text = "ID: ${item.id}", style = TextStyle(color = colorWars))
                }
                /*
                 Planet(
            id = 7,
            name = "Endor",
            rotation_period = "18",
            orbital_period = "402",
            diameter = "4900",
            climate = "temperate",
            gravity = "0.85 standard",
            terrain = "forests, mountains, lakes",
            surface_water = "8",
            population = "30000000",
            residents = listOf("Wicket W. Warrick"),
            films = listOf("Return of the Jedi")
        )
                 */
            }
            }
        }

        }



@Preview(showBackground = true)
@Composable
fun ListPreview()
{
    ListPlanets(modifier = Modifier)
}