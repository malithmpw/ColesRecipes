package com.coles.recipes.navigation

import kotlinx.serialization.Serializable


@Serializable
data object SplashScreen

@Serializable
data object RecipesScreen

@Serializable
data class RecipeDetailsScreen(val recipeId: Int)
