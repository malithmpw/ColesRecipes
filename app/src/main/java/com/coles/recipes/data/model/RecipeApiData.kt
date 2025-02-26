package com.coles.recipes.data.model

import kotlinx.serialization.Serializable


data class RecipesApiData(val recipes: List<RecipeApiData>)

@Serializable
data class RecipeApiData(
    val dynamicTitle: String,
    val dynamicDescription: String,
    val dynamicThumbnail: String,
    val dynamicThumbnailAlt: String,
    val recipeDetails: RecipeDetailsApiData,
    val ingredients: List<IngredientApiData>
)
@Serializable
data class RecipeDetailsApiData(
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
data class IngredientApiData(val ingredient: String)