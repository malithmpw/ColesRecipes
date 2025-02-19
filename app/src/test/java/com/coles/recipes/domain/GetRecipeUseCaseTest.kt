package com.coles.recipes.domain

import com.coles.recipes.data.model.IngredientData
import com.coles.recipes.data.model.RecipeData
import com.coles.recipes.data.model.RecipeDetailsData
import com.coles.recipes.data.repository.RecipesRepository
import com.coles.recipes.util.Result
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
        val mockRecipe = RecipeData(
            "Title",
            "Description",
            "",
            "Description",
            recipeDetails = RecipeDetailsData(
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
                IngredientData("800g butternut pumpkin, peeled, seeded, finely chopped 800g butternut pumpkin, peeled,800g butternut "),
                IngredientData("250g smooth ricotta")
            )
        )
        val mockFlow: Flow<Result<RecipeData?>> = flowOf(Result.Success(mockRecipe))

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
        val mockFlow: Flow<Result<RecipeData?>> = flowOf(Result.Error(Exception(errorMessage)))

        coEvery { repository.getRecipe(recipeId) } returns mockFlow


        val resultFlow = getRecipeUseCase(recipeId)

        resultFlow.collect { result ->
            assertTrue(result is Result.Error)
            assertEquals(errorMessage, (result as Result.Error).exception.message)
        }

        coVerify(exactly = 1) { repository.getRecipe(recipeId) }
    }
}