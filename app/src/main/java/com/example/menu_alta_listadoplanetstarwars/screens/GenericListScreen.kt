package com.example.menu_alta_listadoplanetstarwars.screens

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.composables.deathstaricon
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBarState
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import com.example.menu_alta_listadoplanetstarwars.viewModel.ListadoViewModel

@Composable
fun GenericListScreen(
    type: String,
    viewModel: ListadoViewModel,
    navController: NavController,
    onUpdateTopBar: (BaseTopAppBarState) -> Unit
) {
    // Aquí deberías filtrar o transformar tus datos según el "type"
    val datos by viewModel.planetas.collectAsState(initial = emptyList())
    val menuIcon = painterResource(id = R.drawable.menu_frame)

    LaunchedEffect(type) {
        onUpdateTopBar(
            BaseTopAppBarState(
                title = "Listado de $type",
                iconUpAction = menuIcon,
                actions = listOf()
            )
        )
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(datos) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black)
            ) {
                Row(modifier = Modifier.padding(16.dp)) {

                    Icon(
                        imageVector = if (type == "Planetas") deathstaricon else Icons.Default.List,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        Text(text = item.name, color = colorWars, fontSize = 20.sp)
                        Text(text = "Dato: ${item.climate}", color = Color.White)
                    }
                }
            }
        }
    }
}