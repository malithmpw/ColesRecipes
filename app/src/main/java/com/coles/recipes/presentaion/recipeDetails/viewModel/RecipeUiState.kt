package com.coles.recipes.presentaion.recipeDetails.viewModel

import com.coles.recipes.domain.model.RecipeData

sealed class RecipeUiState {
    data class Success(val recipe: RecipeData?) : RecipeUiState()
    data object Loading : RecipeUiState()
    data class Error(val message: String) : RecipeUiState()
}