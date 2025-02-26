package com.coles.recipes.presentaion.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coles.recipes.ui.theme.coles_red
import com.coles.recipes.ui.theme.coles_red_light

@Composable
fun RecipeLoadingView(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = modifier.width(64.dp),
            color = coles_red,
            trackColor = coles_red_light,
        )
    }
}

@Preview
@Composable
fun PreviewRecipeLoadingView() {
    RecipeLoadingView()
}