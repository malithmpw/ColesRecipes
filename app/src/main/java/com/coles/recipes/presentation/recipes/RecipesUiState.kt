package com.coles.recipes.presentation.recipes

import com.coles.recipes.data.model.RecipesData

sealed class RecipesUiState {
    data class Success(val recipes: RecipesData) : RecipesUiState()
    data object Loading : RecipesUiState()
    data class Error(val message: String) : RecipesUiState()
}