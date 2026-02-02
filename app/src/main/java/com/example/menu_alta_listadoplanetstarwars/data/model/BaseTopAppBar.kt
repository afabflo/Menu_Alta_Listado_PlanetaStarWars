package com.example.menu_alta_listadoplanetstarwars.data.model

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.menu_alta_listadoplanetstarwars.ui.theme.colorWars

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopAppBar(state: BaseTopAppBarState) {
    val visible = state.actions.filter { it.isVisible }
    val notVisible = state.actions.filter { !it.isVisible }
    val fondoBarra = Color.Black
    val colorStarWars = Color(0xFFFFE81F)
    TopAppBar(
        title = {
            Text(
                text = state.title, modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  colorStarWars,
            )
        },
        navigationIcon = {
            IconButton(onClick = state.upAction) {
                state.iconUpAction?.let {
                    Icon(
                        painter = it,
                        contentDescription = "Menu",
                        tint = Color.Unspecified
                    )
                }
            }
        },
        actions = {
            if (visible.isNotEmpty()) visible.forEach {
                IconButton(onClick = it.onClick) {
                    when (it) {
                        is Action.ActionImageVector -> {
                            Icon(
                                imageVector = it.icon!!,
                                contentDescription = it.contentDescription,
                                tint = colorStarWars
                            )
                        }

                        is Action.ActionPainter -> {
                            Icon(
                                painter = it.icon!!,
                                contentDescription = it.contentDescription,
                                tint = colorStarWars
                            )
                        }
                    }
                }
            }
            if (notVisible.isNotEmpty()) TopAppBarDropDownMenu(state.actions.filter { !it.isVisible })
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black
    ))
}
@Composable
fun TopAppBarDropDownMenu(actions: List<Action>) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(
        onClick = {
            expanded = true
        }
    ) {
        Icon(
            Icons.Default.MoreVert,
            contentDescription = "Abrir Menu",
            tint = colorWars
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        actions.forEach {
            DropdownMenuItem(
                text = {
                    Text(it.name)
                },
                onClick = {
                    expanded = false
                    it.onClick()
                },
            )
        }
    }
}

