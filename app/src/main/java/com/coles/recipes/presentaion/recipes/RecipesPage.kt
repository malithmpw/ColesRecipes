package com.coles.recipes.presentaion.recipes

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.coles.recipes.R
import com.coles.recipes.presentaion.common.components.LoadingErrorView
import com.coles.recipes.presentaion.common.components.RecipeLoadingView
import com.coles.recipes.presentaion.recipes.components.RecipeTile
import com.coles.recipes.presentaion.recipes.viewModel.RecipesPageAction
import com.coles.recipes.presentaion.recipes.viewModel.RecipesUiState
import com.coles.recipes.presentaion.recipes.viewModel.RecipesViewModel
import com.coles.recipes.ui.theme.Typography
import com.coles.recipes.ui.theme.coles_red
import com.coles.recipes.presentaion.common.util.BackPressHandler
import com.coles.recipes.domain.model.RecipesData
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesPage(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    goToRecipeDetails: (Int) -> Unit
) {
    val viewModel: RecipesViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val configuration = LocalConfiguration.current
    val isPortrait by remember(configuration) {
        mutableStateOf(configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
    }

    BackPressHandler {
        onBackPressed.invoke()
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        contentWindowInsets = WindowInsets.safeContent,
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.recipes),
                        style = Typography.headlineMedium,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = coles_red)
            )
        }) { innerPadding ->

        when (uiState) {
            is RecipesUiState.Loading -> {
                RecipeLoadingView(Modifier.padding(innerPadding))
            }

            is RecipesUiState.Error -> {
                LoadingErrorView {
                    viewModel.onEvent(RecipesPageAction.LoadRecipes)
                }
                scope.launch {
                    snackbarHostState.showSnackbar("Recipes loading failed")
                }
            }

            is RecipesUiState.Success -> {
                RecipesPageSuccessContent(
                    modifier = modifier.padding(innerPadding),
                    recipes = (uiState as RecipesUiState.Success).recipes,
                    isPortrait = isPortrait,
                    goToRecipeDetails = goToRecipeDetails
                )
            }
        }
    }
}

@Composable
fun RecipesPageSuccessContent(
    modifier: Modifier = Modifier,
    recipes: RecipesData?,
    isPortrait: Boolean,
    goToRecipeDetails: (Int) -> Unit
) {

    Box(
        modifier = modifier
            .background(color = Color.White)
    ) {
        val items = recipes?.recipes ?: listOf()
        if (isPortrait) {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                itemsIndexed(items = items) { index, recipe ->
                    RecipeTile(
                        modifier = Modifier,
                        recipe = recipe,
                        index = index,
                        onRecipeClick = {
                            goToRecipeDetails.invoke(index)
                        })
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(items = items) { index, recipe ->
                    RecipeTile(
                        modifier = Modifier,
                        recipe = recipe,
                        index = index,
                        onRecipeClick = {
                            goToRecipeDetails.invoke(index)
                        })
                }
            }
        }
    }

}


@Preview
@Composable
fun PreviewRecipesPageContent() {
    RecipesPageSuccessContent(
        recipes = RecipesData(recipes = listOf()),
        isPortrait = false,
        goToRecipeDetails = {})
}