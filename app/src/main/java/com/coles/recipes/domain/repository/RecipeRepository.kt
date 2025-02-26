package com.coles.recipes.domain.repository

import com.coles.recipes.presentaion.common.util.Result
import com.coles.recipes.domain.model.RecipeData
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipe(recipeId: Int): Flow<Result<RecipeData?>>
}