package com.coles.recipes.domain.model

data class RecipeData(
    val dynamicTitle: String,
    val dynamicDescription: String,
    val dynamicThumbnail: String,
    val dynamicThumbnailAlt: String,
    val recipeDetails: RecipeDetailsData,
    val ingredients: List<IngredientData>
)
