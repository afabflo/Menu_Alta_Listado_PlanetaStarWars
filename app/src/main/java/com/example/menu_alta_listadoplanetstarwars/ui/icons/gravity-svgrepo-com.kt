package com.composables

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val gravitySvgrepoCom: ImageVector
    get() {
        if (_gravitySvgrepoCom != null) return _gravitySvgrepoCom!!
        
        _gravitySvgrepoCom = ImageVector.Builder(
            name = "gravitySvgrepoCom",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                }
            }
            group {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                }
            }
            group {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                }
            }
            group {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                }
            }
            group {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                }
            }
            group {
                path(
                    fill = SolidColor(Color(0xFF000000))
                ) {
                }
            }
        }.build()
        
        return _gravitySvgrepoCom!!
    }

private var _gravitySvgrepoCom: ImageVector? = null

