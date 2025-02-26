package com.coles.recipes.domain.usecase

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.coles.recipes.presentaion.common.util.Result
import com.coles.recipes.domain.model.RecipesData
import com.coles.recipes.domain.repository.RecipesRepository

class GetRecipesUseCase @Inject constructor(private val repository: RecipesRepository) {
    operator fun invoke(): Flow<Result<RecipesData?>> = repository.getRecipes()
}