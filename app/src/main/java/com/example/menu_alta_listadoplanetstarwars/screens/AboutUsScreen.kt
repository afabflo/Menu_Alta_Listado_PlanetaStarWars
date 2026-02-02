package com.example.menu_alta_listadoplanetstarwars.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.menu_alta_listadoplanetstarwars.R
import com.example.menu_alta_listadoplanetstarwars.data.model.BaseTopAppBarState

@Composable
fun AboutUsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onUpdateTopBar: (BaseTopAppBarState) -> Unit // callback
) {
    val menuIcon = painterResource(id = R.drawable.ic_launcher)

    LaunchedEffect(Unit) {
        onUpdateTopBar(
            BaseTopAppBarState(
                title = "Sobre Nosotros",
                iconUpAction = menuIcon,
                upAction = { navController.popBackStack()},
                actions = emptyList()
            )
        )
    }

    Column(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.fotoabout),
            contentDescription = "Imagen de Star Wars",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        HorizontalDivider()

        Text(
            text = "Sobre Nosotros",
            fontSize = 26.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        HorizontalDivider()

        Text(
            text = "Aplicación creada por Aitor Fabian Flores.",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 14.dp, start = 16.dp, end = 16.dp)
        )

        Text(
            text = "Proyecto: Menú, Alta y Listado de Planetas Star Wars",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 14.dp, start = 16.dp, end = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutUsview() {

}