package com.coles.recipes.domain


import com.coles.recipes.data.model.RecipesData
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

class GetRecipesUseCaseTest {

    private lateinit var repository: RecipesRepository
    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @Before
    fun setup() {
        repository = mockk()
        getRecipesUseCase = GetRecipesUseCase(repository)
    }

    @Test
    fun `invoke should return list of recipes when repository succeeds`() = runTest {

        val mockRecipes = RecipesData(listOf())
        val mockFlow: Flow<Result<RecipesData>> = flowOf(Result.Success(mockRecipes))

        coEvery { repository.getRecipes() } returns mockFlow


        val resultFlow = getRecipesUseCase()


        resultFlow.collect { result ->
            assertTrue(result is Result.Success)
            assertEquals(mockRecipes, (result as Result.Success).data)
        }

        coVerify(exactly = 1) { repository.getRecipes() }
    }

    @Test
    fun `invoke should return error when repository fails`() = runTest {

        val errorMessage = "Error fetching recipes"
        val mockFlow: Flow<Result<RecipesData>> = flowOf(Result.Error(Exception(errorMessage)))

        coEvery { repository.getRecipes() } returns mockFlow


        val resultFlow = getRecipesUseCase()

        resultFlow.collect { result ->
            assertTrue(result is Result.Error)
            assertEquals(errorMessage, (result as Result.Error).exception.message)
        }

        coVerify(exactly = 1) { repository.getRecipes() }
    }
}
