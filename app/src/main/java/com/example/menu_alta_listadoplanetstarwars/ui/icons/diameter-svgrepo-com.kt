package com.composables

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val diameterSvgrepoCom: ImageVector
    get() {
        if (_diameterSvgrepoCom != null) return _diameterSvgrepoCom!!
        
        _diameterSvgrepoCom = ImageVector.Builder(
            name = "diameterSvgrepoCom",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(21f, 12f)
                arcTo(9f, 9f, 0f, false, true, 12f, 21f)
                arcTo(9f, 9f, 0f, false, true, 3f, 12f)
                arcTo(9f, 9f, 0f, false, true, 21f, 12f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(16f, 13f)
                lineTo(17f, 12f)
                lineTo(16f, 11f)
            }
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(8f, 11f)
                lineTo(7f, 12f)
                lineTo(8f, 13f)
            }
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(7f, 12f)
                horizontalLineTo(17f)
                moveToRelative(4f, 0f)
                arcToRelative(9f, 9f, 0f, true, false, -9f, 9f)
                arcTo(9f, 9f, 0f, false, false, 21f, 12f)
                close()
            }
        }.build()
        
        return _diameterSvgrepoCom!!
    }

private var _diameterSvgrepoCom: ImageVector? = null

