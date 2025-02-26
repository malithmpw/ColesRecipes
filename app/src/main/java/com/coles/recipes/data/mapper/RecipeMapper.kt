package com.coles.recipes.data.mapper

import com.coles.recipes.data.model.RecipeApiData
import com.coles.recipes.data.model.RecipesApiData
import com.coles.recipes.domain.model.IngredientData
import com.coles.recipes.domain.model.RecipeData
import com.coles.recipes.domain.model.RecipeDetailsData
import com.coles.recipes.domain.model.RecipesData

fun RecipeApiData?.toRecipeData(): RecipeData? {
    if (this == null) {
        return null
    }
    return RecipeData(
        dynamicTitle = dynamicTitle,
        dynamicThumbnail = dynamicThumbnail,
        dynamicDescription = dynamicDescription,
        dynamicThumbnailAlt = dynamicThumbnailAlt,
        recipeDetails = RecipeDetailsData(
            amountLabel = recipeDetails.amountLabel,
            amountNumber = recipeDetails.amountNumber,
            prepLabel = recipeDetails.prepLabel,
            prepTime = recipeDetails.prepTime,
            prepNote = recipeDetails.prepNote,
            cookingLabel = recipeDetails.cookingLabel,
            cookingTime = recipeDetails.cookingTime,
            cookTimeAsMinutes = recipeDetails.cookTimeAsMinutes,
            prepTimeAsMinutes = recipeDetails.prepTimeAsMinutes
        ),
        ingredients = ingredients.map { IngredientData(it.ingredient) }
    )
}


fun RecipesApiData?.toRecipesData(): RecipesData? {
    if (this == null) {
        return null
    }
    val data = this.recipes.map {
        RecipeData(
            dynamicTitle = it.dynamicTitle,
            dynamicThumbnail = it.dynamicThumbnail,
            dynamicDescription = it.dynamicDescription,
            dynamicThumbnailAlt = it.dynamicThumbnailAlt,
            recipeDetails = RecipeDetailsData(
                amountLabel = it.recipeDetails.amountLabel,
                amountNumber = it.recipeDetails.amountNumber,
                prepLabel = it.recipeDetails.prepLabel,
                prepTime = it.recipeDetails.prepTime,
                prepNote = it.recipeDetails.prepNote,
                cookingLabel = it.recipeDetails.cookingLabel,
                cookingTime = it.recipeDetails.cookingTime,
                cookTimeAsMinutes = it.recipeDetails.cookTimeAsMinutes,
                prepTimeAsMinutes = it.recipeDetails.prepTimeAsMinutes
            ),
            ingredients = it.ingredients.map { ingredient -> IngredientData(ingredient.ingredient) }
        )
    }
    return RecipesData(data)
}