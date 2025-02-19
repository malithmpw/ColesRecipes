package com.coles.recipes.data.repository

import android.content.Context
import com.coles.recipes.R
import com.coles.recipes.data.model.RecipeData
import com.coles.recipes.data.model.RecipesData
import com.coles.recipes.util.Result
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

@Singleton
class RecipesRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) :
    RecipesRepository {

    override suspend fun getRecipes(): Flow<Result<RecipesData>> = flow {
        emit(Result.Loading)
        try {
            val inputStream = context.resources.openRawResource(R.raw.recipes)
            val reader = InputStreamReader(inputStream)

            val result: RecipesData = Gson().fromJson(reader, RecipesData::class.java)

            delay(2.seconds)

            emit(Result.Success(result))

        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun getRecipe(recipeId: Int): Flow<Result<RecipeData?>> = flow {
        emit(Result.Loading)
        try {
            val inputStream = context.resources.openRawResource(R.raw.recipes)
            val reader = InputStreamReader(inputStream)

            val result: RecipesData = Gson().fromJson(reader, RecipesData::class.java)

            delay(2.seconds)

            emit(Result.Success(result.recipes.getOrNull(recipeId)))

        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}