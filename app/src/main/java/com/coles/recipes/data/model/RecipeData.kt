package com.coles.recipes.data.model

import kotlinx.serialization.Serializable


data class RecipesData(val recipes: List<RecipeData>)

@Serializable
data class RecipeData(
    val dynamicTitle: String,
    val dynamicDescription: String,
    val dynamicThumbnail: String,
    val dynamicThumbnailAlt: String,
    val recipeDetails: RecipeDetailsData,
    val ingredients: List<IngredientData>
)
@Serializable
data class RecipeDetailsData(
    val amountLabel: String,
    val amountNumber: Int,
    val prepLabel: String,
    val prepTime: String,
    val prepNote: String,
    val cookingLabel: String,
    val cookingTime: String,
    val cookTimeAsMinutes: Int,
    val prepTimeAsMinutes: Int,
)

@Serializable
data class IngredientData(val ingredient: String)