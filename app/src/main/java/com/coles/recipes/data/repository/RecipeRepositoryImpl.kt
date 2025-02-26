package com.coles.recipes.data.repository

import android.content.Context
import com.coles.recipes.R
import com.coles.recipes.presentaion.common.util.Result
import com.coles.recipes.data.model.RecipesApiData
import com.coles.recipes.domain.model.RecipeData
import com.coles.recipes.data.mapper.toRecipeData
import com.coles.recipes.domain.repository.RecipeRepository
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.InputStreamReader
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class RecipeRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) :
    RecipeRepository {
    override fun getRecipe(recipeId: Int): Flow<Result<RecipeData?>> = flow {
        emit(Result.Loading)
        try {
            val inputStream = context.resources.openRawResource(R.raw.recipes)
            val reader = InputStreamReader(inputStream)

            val result: RecipesApiData = Gson().fromJson(reader, RecipesApiData::class.java)

            delay(2.seconds)
            val apiData = result.recipes.getOrNull(recipeId)
            emit(Result.Success(apiData.toRecipeData()))

        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}