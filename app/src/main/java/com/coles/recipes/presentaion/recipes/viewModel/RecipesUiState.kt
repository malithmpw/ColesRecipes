package com.coles.recipes.presentaion.recipes.viewModel

import com.coles.recipes.domain.model.RecipesData

sealed class RecipesUiState {
    data class Success(val recipes: RecipesData?) : RecipesUiState()
    data object Loading : RecipesUiState()
    data class Error(val message: String) : RecipesUiState()
}