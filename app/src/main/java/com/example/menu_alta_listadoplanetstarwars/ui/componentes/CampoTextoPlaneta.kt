package com.example.menu_alta_listadoplanetstarwars.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CampoTextoPlaneta(
    valor: String,
    onCHange: (String) -> Unit,
    etiqueta: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onCHange,
        label = { Text(etiqueta) },
        modifier = modifier.fillMaxWidth()
    )
}
