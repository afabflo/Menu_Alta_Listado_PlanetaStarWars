package com.composables

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val terrainicon: ImageVector
    get() {
        if (_terrainSvgrepoCom != null) return _terrainSvgrepoCom!!
        
        _terrainSvgrepoCom = ImageVector.Builder(
            name = "terrainSvgrepoCom",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(40.841f, 312f)
                lineTo(144.493f, 199.12f)
                lineTo(216.397f, 271.024f)
                lineTo(216.397f, 271.024f)
                lineTo(292.687f, 347.313f)
                lineTo(315.313f, 324.687f)
                lineTo(238.244f, 247.617f)
                lineTo(327.738f, 151.73f)
                lineTo(470.836f, 312f)
                lineTo(496f, 312f)
                lineTo(496f, 292.136f)
                lineTo(328.262f, 104.27f)
                lineTo(215.603f, 224.976f)
                lineTo(143.507f, 152.88f)
                lineTo(16f, 291.741f)
                lineTo(16f, 312f)
                lineTo(40.841f, 312f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(16f, 392f)
                horizontalLineTo(496f)
                verticalLineTo(424f)
                horizontalLineTo(16f)
                verticalLineTo(392f)
                close()
            }
        }.build()
        
        return _terrainSvgrepoCom!!
    }

private var _terrainSvgrepoCom: ImageVector? = null

