package com.coles.recipes.di

import android.content.Context
import com.coles.recipes.data.repository.RecipeRepositoryImpl
import com.coles.recipes.data.repository.RecipesRepositoryImpl
import com.coles.recipes.domain.repository.RecipeRepository
import com.coles.recipes.domain.usecase.GetRecipeUseCase
import com.coles.recipes.domain.repository.RecipesRepository
import com.coles.recipes.domain.usecase.GetRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRecipesRepository(@ApplicationContext context: Context): RecipesRepository {
        return RecipesRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(@ApplicationContext context: Context): RecipeRepository {
        return RecipeRepositoryImpl(context)
    }

    @Provides
    fun provideRecipesUseCase(repository: RecipesRepository): GetRecipesUseCase {
        return GetRecipesUseCase(repository)
    }

    @Provides
    fun provideRecipeUseCase(repository: RecipeRepository): GetRecipeUseCase {
        return GetRecipeUseCase(repository)
    }
}