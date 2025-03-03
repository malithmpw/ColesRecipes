package com.coles.recipes.data.repository

import android.content.Context
import com.coles.recipes.R
import com.coles.recipes.data.mapper.toRecipesData
import com.coles.recipes.data.model.RecipesApiData
import com.coles.recipes.presentaion.common.util.Result
import com.coles.recipes.domain.model.RecipesData
import com.coles.recipes.domain.repository.RecipesRepository
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

    override fun getRecipes(): Flow<Result<RecipesData?>> = flow {
        emit(Result.Loading)
        try {

            val inputStream = context.resources.openRawResource(R.raw.recipes)
            val reader = InputStreamReader(inputStream)

            val result: RecipesApiData = Gson().fromJson(reader, RecipesApiData::class.java)

            delay(2.seconds)

            emit(Result.Success(result.toRecipesData()))

        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}