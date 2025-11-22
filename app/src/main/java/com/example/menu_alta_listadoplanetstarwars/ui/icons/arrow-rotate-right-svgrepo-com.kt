package com.composables

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val arrowRotateRightSvgrepoCom: ImageVector
    get() {
        if (_arrowRotateRightSvgrepoCom != null) return _arrowRotateRightSvgrepoCom!!
        
        _arrowRotateRightSvgrepoCom = ImageVector.Builder(
            name = "arrowRotateRightSvgrepoCom",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(10f, 7f)
                lineTo(9f, 6f)
                lineTo(11.2929f, 3.70711f)
                lineTo(10.8013f, 3.21553f)
                curveTo(10.023f, 2.43724f, 8.96744f, 2f, 7.86677f, 2f)
                curveTo(4.63903f, 2f, 2f, 4.68015f, 2f, 7.93274f)
                curveTo(2f, 11.2589f, 4.69868f, 14f, 8f, 14f)
                curveTo(9.53708f, 14f, 11.0709f, 13.4144f, 12.2426f, 12.2426f)
                lineTo(13.6569f, 13.6569f)
                curveTo(12.095f, 15.2188f, 10.0458f, 16f, 8f, 16f)
                curveTo(3.56933f, 16f, 0f, 12.3385f, 0f, 7.93274f)
                curveTo(0f, 3.60052f, 3.50968f, 0f, 7.86677f, 0f)
                curveTo(9.49787f, 0f, 11.0622f, 0.647954f, 12.2155f, 1.80132f)
                lineTo(12.7071f, 2.29289f)
                lineTo(15f, 0f)
                lineTo(16f, 1f)
                verticalLineTo(7f)
                horizontalLineTo(10f)
                close()
            }
        }.build()
        
        return _arrowRotateRightSvgrepoCom!!
    }

private var _arrowRotateRightSvgrepoCom: ImageVector? = null

