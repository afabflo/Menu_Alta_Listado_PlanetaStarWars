package com.example.menu_alta_listadoplanetstarwars.data.model

import android.app.Notification
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Action(
    open val name: String,
    open val contentDescription: String,
    open val onClick: () -> Unit,
    open val isVisible: Boolean = true
) {
    data class ActionPainter(
        override val name:String,
        val icon: Painter?,
        override val contentDescription:String,
        override val onClick: ()->Unit,
        override val isVisible:Boolean=true
    ): Action(name, contentDescription, onClick ,isVisible)


    data class ActionImageVector(
        override val name: String,
        val icon: ImageVector?,
        override val contentDescription: String,
        override val onClick: () -> Unit,
        override val isVisible: Boolean = true
    ) : Action(name, contentDescription, onClick, isVisible)
}

data class BaseTopAppBarState(
    val title: String = "",
    val iconUpAction: Painter? = null,
    val upAction: () -> Unit = {},
    val actions: List<Action> = emptyList()
)