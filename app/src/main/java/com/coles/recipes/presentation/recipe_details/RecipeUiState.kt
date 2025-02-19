package com.coles.recipes.presentation.recipe_details

import com.coles.recipes.data.model.RecipeData

sealed class RecipeUiState {
    data class Success(val recipe: RecipeData?) : RecipeUiState()
    data object Loading : RecipeUiState()
    data class Error(val message: String) : RecipeUiState()
}