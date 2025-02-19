package com.coles.recipes.data.repository

import com.coles.recipes.data.model.RecipeData
import com.coles.recipes.data.model.RecipesData
import kotlinx.coroutines.flow.Flow
import com.coles.recipes.util.Result

interface RecipesRepository {
   suspend fun getRecipes(): Flow<Result<RecipesData>>
   suspend fun getRecipe(recipeId:Int): Flow<Result<RecipeData?>>
}