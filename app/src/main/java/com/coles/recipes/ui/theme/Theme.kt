package com.coles.recipes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = coles_red,
    secondary = coles_red_light,
    tertiary = Color.White,
    background = Color.White,
    surface = Color.Black
)

@Composable
fun ColesRecipesAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}