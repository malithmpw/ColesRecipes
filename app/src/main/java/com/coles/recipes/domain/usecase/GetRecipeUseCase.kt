package com.coles.recipes.domain.usecase

import com.coles.recipes.presentaion.common.util.Result
import com.coles.recipes.domain.repository.RecipeRepository
import com.coles.recipes.domain.model.RecipeData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(private val repository: RecipeRepository) {
    operator fun invoke(recipeId: Int): Flow<Result<RecipeData?>> =
        repository.getRecipe(recipeId)
}