package com.coles.recipes.domain

import com.coles.recipes.data.model.RecipeData
import com.coles.recipes.data.repository.RecipesRepository
import com.coles.recipes.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(private val repository: RecipesRepository) {
    suspend operator fun invoke(recipeId: Int): Flow<Result<RecipeData?>> =
        repository.getRecipe(recipeId)
}