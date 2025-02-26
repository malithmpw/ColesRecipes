package com.coles.recipes.domain.repository

import com.coles.recipes.presentaion.common.util.Result
import com.coles.recipes.domain.model.RecipesData
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    fun getRecipes(): Flow<Result<RecipesData?>>
}