package com.composables

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val climateGlobal: ImageVector
    get() {
        if (_globalWarmingSvgrepoCom != null) return _globalWarmingSvgrepoCom!!
        
        _globalWarmingSvgrepoCom = ImageVector.Builder(
            name = "globalWarmingSvgrepoCom",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(21.55f, 16.18f)
                verticalLineTo(12f)
                arcToRelative(2.87f, 2.87f, 0f, false, false, -5.73f, 0f)
                verticalLineToRelative(4.18f)
                arcToRelative(3.77f, 3.77f, 0f, false, false, -1f, 2.5f)
                arcToRelative(3.82f, 3.82f, 0f, true, false, 6.69f, -2.5f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(19.63f, 18.68f)
                arcTo(0.95f, 0.95f, 0f, false, true, 18.68f, 19.63f)
                arcTo(0.95f, 0.95f, 0f, false, true, 17.73f, 18.68f)
                arcTo(0.95f, 0.95f, 0f, false, true, 19.63f, 18.68f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(18.68f, 13f)
                verticalLineToRelative(4.78f)
            }
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(21.6f, 16.26f)
                arcTo(10.5f, 10.5f, 0f, false, false, 4.32f, 4.84f)
                arcTo(10.5f, 10.5f, 0f, false, false, 16.24f, 21.61f)
            }
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(15.6f, 16.46f)
                lineToRelative(-0.14f, -0.05f)
                arcTo(10.51f, 10.51f, 0f, false, false, 4.32f, 19.16f)
            }
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(4.32f, 4.84f)
                arcTo(10.47f, 10.47f, 0f, false, false, 12f, 8.18f)
                arcToRelative(10.47f, 10.47f, 0f, false, false, 7.68f, -3.34f)
            }
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(15.4f, 16.76f)
                arcToRelative(2f, 2f, 0f, false, false, 0.06f, -0.35f)
                arcTo(26.33f, 26.33f, 0f, false, false, 15.82f, 12f)
                arcToRelative(26.33f, 26.33f, 0f, false, false, -0.36f, -4.41f)
                curveToRelative(-0.58f, -3.45f, -1.81f, -5.87f, -3.26f, -6.08f)
                horizontalLineToRelative(-0.4f)
                curveToRelative(-1.45f, 0.21f, -2.68f, 2.63f, -3.26f, 6.08f)
                arcTo(26.33f, 26.33f, 0f, false, false, 8.18f, 12f)
                arcToRelative(26.33f, 26.33f, 0f, false, false, 0.36f, 4.41f)
                curveToRelative(0.58f, 3.45f, 1.81f, 5.87f, 3.26f, 6.08f)
                horizontalLineToRelative(0.4f)
                curveToRelative(1.06f, -0.15f, 2f, -1.51f, 2.67f, -3.58f)
            }
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(1.5f, 12f)
                lineTo(15.82f, 12f)
            }
        }.build()
        
        return _globalWarmingSvgrepoCom!!
    }

private var _globalWarmingSvgrepoCom: ImageVector? = null

