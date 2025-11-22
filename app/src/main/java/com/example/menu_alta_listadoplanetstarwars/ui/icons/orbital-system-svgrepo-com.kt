package com.composables

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val orbitalSystemSvgrepoCom: ImageVector
    get() {
        if (_orbitalSystemSvgrepoCom != null) return _orbitalSystemSvgrepoCom!!
        
        _orbitalSystemSvgrepoCom = ImageVector.Builder(
            name = "orbitalSystemSvgrepoCom",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 393.819f,
            viewportHeight = 393.819f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                }
            }
        }.build()
        
        return _orbitalSystemSvgrepoCom!!
    }

private var _orbitalSystemSvgrepoCom: ImageVector? = null

