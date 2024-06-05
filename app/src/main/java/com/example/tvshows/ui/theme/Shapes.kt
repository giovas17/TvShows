package com.example.tvshows.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class AppShapes(
    val tiny: Shape,
    val small: Shape,
    val medium: Shape,
    val middle: Shape,
    val big: Shape,
    val chip: Shape,
    val button: Shape,
    val buttonCircular: Shape,
    val bottomSheet: Shape,
)

val defaultShapes: AppShapes = AppShapes(
    tiny = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(6.dp),
    medium = RoundedCornerShape(8.dp),
    middle = RoundedCornerShape(20.dp),
    big = RoundedCornerShape(30.dp),
    chip = RoundedCornerShape(100.dp),
    button = RoundedCornerShape(6.dp),
    buttonCircular = RoundedCornerShape(100.dp),
    bottomSheet = RoundedCornerShape(topStart = 25.dp, topEnd = 35.dp),
)

internal val LocalAppShapes = staticCompositionLocalOf { defaultShapes }