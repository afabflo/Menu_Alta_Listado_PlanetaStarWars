package com.example.menu_alta_listadoplanetstarwars.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars
import com.example.menu_alta_listadoplanetstarwars.viewModel.PersonViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListPeople(
    modifier: Modifier = Modifier,
    viewModel: PersonViewModel,
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {
    val people by viewModel.people.collectAsState(initial = emptyList())

    // Gradiente Nacarado Púrpura/Oro
    val personGradient = androidx.compose.ui.graphics.Brush.verticalGradient(
        colors = listOf(Color(0xFF4A148C), Color(0xFFAB47BC), Color(0xFFFFD700).copy(alpha = 0.3f))
    )

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(people) { person ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .combinedClickable(
                        onClick = {  },
                        onLongClick = { }
                    ),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box(modifier = Modifier.background(personGradient).padding(16.dp).fillMaxWidth()) {
                    androidx.compose.foundation.layout.Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                        // Aquí puedes poner un icono o la imagen del personaje
                        Icon(Icons.Default.Info, contentDescription = null, tint = Color.White, modifier = Modifier.size(40.dp))
                        androidx.compose.foundation.layout.Spacer(Modifier.width(16.dp))
                        Column {
                            Text(person.name, color = Color.White, fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold)
                            Text("Género: ${person.gender}", color = Color.LightGray)
                            Text("Año: ${person.birthYear}", color = colorWars)
                        }
                    }
                }
            }
        }
    }
}