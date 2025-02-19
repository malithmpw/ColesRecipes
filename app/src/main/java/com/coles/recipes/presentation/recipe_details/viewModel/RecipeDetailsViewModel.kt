package com.coles.recipes.presentation.recipe_details.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coles.recipes.domain.GetRecipeUseCase
import com.coles.recipes.presentation.recipe_details.RecipeUiState
import com.coles.recipes.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

sealed interface RecipeDetailsAction {
    data class LoadRecipe(val recipeId: Int) : RecipeDetailsAction
}

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(private val recipeUseCase: GetRecipeUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
    val uiState: StateFlow<RecipeUiState> = _uiState

    fun onAction(recipeDetailsAction: RecipeDetailsAction) {
        when (recipeDetailsAction) {
            is RecipeDetailsAction.LoadRecipe -> {
                getRecipes(recipeDetailsAction.recipeId)
            }
        }
    }

    private fun getRecipes(recipeId: Int) {
        viewModelScope.launch {
            recipeUseCase.invoke(recipeId).collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        // This is to mimic api failure/errors
                        val random = Random.nextBoolean()
                        if (random) {
                            RecipeUiState.Success(result.data)
                        } else {
                            RecipeUiState.Error(
                                "Unknown error"
                            )
                        }
                    }

                    is Result.Loading -> RecipeUiState.Loading
                    is Result.Error -> RecipeUiState.Error(
                        result.exception.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}