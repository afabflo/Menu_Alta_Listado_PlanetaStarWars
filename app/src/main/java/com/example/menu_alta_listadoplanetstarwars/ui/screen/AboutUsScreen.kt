package com.example.menu_alta_listadoplanetstarwars.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.menu_alta_listadoplanetstarwars.R

@Composable
fun AboutUsScreen(modifier: Modifier) {
    Column() {
        Image(
            painter = painterResource(R.drawable.fotoabout),
            contentDescription = ""
            , modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Divider()
            Text(text = "Sobre Nosotros", fontSize = 26.sp, fontFamily = FontFamily.Monospace)
        Divider()
            Text(
                text = "Aplicación creada por Aitor Fabian Flores.",
                fontSize = 18.sp,modifier = Modifier.padding(top = 14.dp)
            )
            Text(
                text = "Proyecto: Menú, Alta y Listado de Planetas Star Wars",
                fontSize = 16.sp,modifier = Modifier.padding(top = 14.dp)
            )
        }

}

@Preview(showBackground = true)
@Composable
fun AboutUsview(){
    AboutUsScreen(modifier = Modifier)
}