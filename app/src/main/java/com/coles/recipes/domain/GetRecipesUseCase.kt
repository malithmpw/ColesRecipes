package com.coles.recipes.domain

import com.coles.recipes.data.model.RecipesData
import com.coles.recipes.data.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.coles.recipes.util.Result

class GetRecipesUseCase @Inject constructor(private val repository: RecipesRepository) {
    suspend operator fun invoke(): Flow<Result<RecipesData>> = repository.getRecipes()
}