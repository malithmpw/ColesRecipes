package com.coles.recipes.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.coles.recipes.presentation.recipe_details.RecipeDetailsPage
import com.coles.recipes.presentation.recipes.RecipesPage
import com.coles.recipes.presentation.splash.SplashScreenPage

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SplashScreen
    ) {
        composable<SplashScreen> {
            SplashScreenPage {
                navController.navigate(RecipesScreen) {
                    popUpTo(SplashScreen) { inclusive = true }
                }
            }
        }
        composable<RecipesScreen> {
            val activity = LocalContext.current as? Activity
            RecipesPage(
                onBackPressed = {
                    activity?.finish()
                }
            ) { recipeId ->
                navController.navigate(RecipeDetailsScreen(recipeId = recipeId))
            }
        }
        composable<RecipeDetailsScreen> {
            val data = it.toRoute<RecipeDetailsScreen>().recipeId
            RecipeDetailsPage(recipeId = data)
        }
    }
}