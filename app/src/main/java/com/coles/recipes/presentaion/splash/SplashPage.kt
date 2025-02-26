package com.coles.recipes.presentaion.splash

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.coles.recipes.ui.theme.Typography
import com.coles.recipes.ui.theme.coles_red

@Composable
fun SplashScreenPage(navigateToDestination: () -> Unit) {
    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToDestination.invoke()
        }, 2000)
    }
    SplashScreenPageContent()
}

@Composable
fun SplashScreenPageContent(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = coles_red),
        contentAlignment = Alignment.Center
    ) {
        Text("Coles", style = Typography.displayLarge, color = Color.White)
    }
}

@Preview
@Composable
fun PreviewSplashScreenPageContent() {
    SplashScreenPageContent()
}