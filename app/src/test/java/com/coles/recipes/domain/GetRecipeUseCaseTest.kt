package com.coles.recipes.domain

import com.coles.recipes.data.model.IngredientApiData
import com.coles.recipes.data.model.RecipeApiData
import com.coles.recipes.data.model.RecipeDetailsApiData
import com.coles.recipes.domain.usecase.GetRecipeUseCase
import com.coles.recipes.presentaion.common.util.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetRecipeUseCaseTest {

    private lateinit var repository: RecipesRepository
    private lateinit var getRecipeUseCase: GetRecipeUseCase

    @Before
    fun setup() {
        repository = mockk()
        getRecipeUseCase = GetRecipeUseCase(repository)
    }

    @Test
    fun `invoke should return recipe when repository succeeds`() = runTest {

        val recipeId = 1
        val mockRecipe = RecipeApiData(
            "Title",
            "Description",
            "",
            "Description",
            recipeDetails = RecipeDetailsApiData(
                amountLabel = "Serves",
                amountNumber = 8,
                prepLabel = "Prep",
                prepTime = "15m",
                prepNote = "+ cooking time",
                cookingLabel = "Cooking",
                cookingTime = "15m",
                cookTimeAsMinutes = 15,
                prepTimeAsMinutes = 20
            ),
            ingredients = listOf(
                IngredientApiData("800g butternut pumpkin, peeled, seeded, finely chopped 800g butternut pumpkin, peeled,800g butternut "),
                IngredientApiData("250g smooth ricotta")
            )
        )
        val mockFlow: Flow<Result<RecipeApiData?>> = flowOf(Result.Success(mockRecipe))

        coEvery { repository.getRecipe(recipeId) } returns mockFlow


        val resultFlow = getRecipeUseCase(recipeId)

        resultFlow.collect { result ->
            assertTrue(result is Result.Success)
            assertEquals(mockRecipe, (result as Result.Success).data)
        }

        coVerify(exactly = 1) { repository.getRecipe(recipeId) }
    }

    @Test
    fun `invoke should return error when repository fails`() = runTest {

        val recipeId = 1
        val errorMessage = "Error fetching recipe"
        val mockFlow: Flow<Result<RecipeApiData?>> = flowOf(Result.Error(Exception(errorMessage)))

        coEvery { repository.getRecipe(recipeId) } returns mockFlow


        val resultFlow = getRecipeUseCase(recipeId)

        resultFlow.collect { result ->
            assertTrue(result is Result.Error)
            assertEquals(errorMessage, (result as Result.Error).exception.message)
        }

        coVerify(exactly = 1) { repository.getRecipe(recipeId) }
    }
}