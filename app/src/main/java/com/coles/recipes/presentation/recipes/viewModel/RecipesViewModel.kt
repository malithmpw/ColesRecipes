package com.coles.recipes.presentation.recipes.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coles.recipes.domain.GetRecipesUseCase
import com.coles.recipes.presentation.recipes.RecipesUiState
import com.coles.recipes.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

sealed interface RecipesPageAction {
    data object LoadRecipes : RecipesPageAction
}

@HiltViewModel
class RecipesViewModel @Inject constructor(private val recipesUseCase: GetRecipesUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<RecipesUiState>(RecipesUiState.Loading)
    val uiState: StateFlow<RecipesUiState> = _uiState

    init {
        getRecipes()
    }

    fun onEvent(recipesPageAction: RecipesPageAction) {
        when (recipesPageAction) {
            is RecipesPageAction.LoadRecipes -> {
                getRecipes()
            }
        }
    }

    private fun getRecipes() {
        viewModelScope.launch {
            recipesUseCase.invoke().collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        // This is to mimic api failure/errors
                        val random = Random.nextBoolean()
                        if (random) {
                            RecipesUiState.Success(result.data)
                        } else {
                            RecipesUiState.Error(
                                "Unknown error"
                            )
                        }
                    }

                    is Result.Loading -> RecipesUiState.Loading
                    is Result.Error -> RecipesUiState.Error(
                        result.exception.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}