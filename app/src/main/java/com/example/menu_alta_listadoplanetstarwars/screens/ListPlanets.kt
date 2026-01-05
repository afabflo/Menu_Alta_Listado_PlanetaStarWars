package com.example.menu_alta_listadoplanetstarwars.screens

import Planet
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.composables.deathstaricon
import com.example.menu_alta_listadoplanetstarwars.home.Routes
import com.example.menu_alta_listadoplanetstarwars.ui.locals.LocalPlanetPadding
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import com.example.menu_alta_listadoplanetstarwars.viewModel.ListadoViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPlanets(
modifier: Modifier = Modifier,
viewModel: ListadoViewModel,
navController: NavController,
snackbarHostState : SnackbarHostState
) {
    val planetas by viewModel.planetas.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var planetToDelete by remember { mutableStateOf<Planet?>(null) }
    val scope = rememberCoroutineScope()
    LazyColumn(
        modifier = modifier.fillMaxSize()){
        items(planetas){
            item ->
            Card (
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 12.dp).combinedClickable(onClick = {
                    viewModel.selecionarPlaneta(item)
                    navController.navigate(Routes.EDIT)

                },
                    onLongClick = {
                        planetToDelete = item
                        showDialog = true
                    }
                ),
                shape = CardDefaults.shape,
                colors = CardDefaults.cardColors(containerColor = Color.Black),
            ){
                Column(
                    modifier = Modifier.padding(LocalPlanetPadding.current),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
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

            }
            }


        }
    if(showDialog){
        AlertDialog(
            onDismissRequest = {showDialog = false},
            title = {Text(text = "Eliminar Planeta")},
            text = {Text(text = "Estas seguro de que quieres borrar a ${planetToDelete?.name} ")},
            confirmButton = {
                TextButton(onClick = {
                    planetToDelete?.let { viewModel.borrarPlaneta(it) }
                    showDialog = false
                    scope.launch {
                        snackbarHostState.showSnackbar("Planeta eliminado")
                    }
                }) { Text("Eliminar") }
            }, dismissButton = {
                TextButton(onClick = {showDialog= false}) {
                    Text(text="Cancelar",color =colorWars)
                }
            }
        )
    }
        }



@Preview(showBackground = true)
@Composable
fun ListPreview()
{
    //ListPlanets(modifier = Modifier)
}