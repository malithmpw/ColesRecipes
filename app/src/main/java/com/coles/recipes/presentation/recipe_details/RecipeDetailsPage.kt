package com.coles.recipes.presentation.recipe_details

import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.coles.recipes.R
import com.coles.recipes.data.model.IngredientData
import com.coles.recipes.data.model.RecipeData
import com.coles.recipes.data.model.RecipeDetailsData
import com.coles.recipes.presentation.common.LoadImage
import com.coles.recipes.presentation.common.LoadingErrorView
import com.coles.recipes.presentation.common.RecipeLoadingView
import com.coles.recipes.presentation.recipe_details.viewModel.RecipeDetailsAction
import com.coles.recipes.presentation.recipe_details.viewModel.RecipeDetailsViewModel
import com.coles.recipes.ui.theme.Typography
import com.coles.recipes.ui.theme.light_black
import com.coles.recipes.util.LockScreenOrientation

@Composable
fun RecipeDetailsPage(modifier: Modifier = Modifier, recipeId: Int) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val viewModel: RecipeDetailsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onAction(RecipeDetailsAction.LoadRecipe(recipeId))
    }

    Scaffold(
        contentWindowInsets = WindowInsets.safeContent,
        modifier = modifier,
    ) { innerPadding ->

        when (uiState) {
            is RecipeUiState.Loading -> {
                RecipeLoadingView(Modifier.padding(innerPadding))
            }

            is RecipeUiState.Error -> {
                LoadingErrorView {
                    viewModel.onAction(RecipeDetailsAction.LoadRecipe(recipeId))
                }
            }

            is RecipeUiState.Success -> {
                RecipeDetailsContent(
                    modifier = Modifier,
                    (uiState as RecipeUiState.Success).recipe,
                    recipeId = recipeId
                )
            }
        }
        Box(modifier = Modifier.padding(innerPadding))
    }
}


@Composable
fun RecipeDetailsContent(modifier: Modifier = Modifier, recipe: RecipeData?, recipeId: Int) {
    recipe?.let {
        LazyColumn(
            modifier = modifier
                .background(color = Color.White)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    modifier = Modifier.padding(24.dp),
                    text = it.dynamicTitle,
                    style = Typography.displayMedium,
                    color = Color.Black, maxLines = 4,
                    textAlign = TextAlign.Center
                )
            }
            item {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = it.dynamicDescription,
                    style = Typography.labelLarge,
                    color = Color.Black, maxLines = 4,
                    textAlign = TextAlign.Center
                )
            }
            item {
                val url =
                    "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505"
                /**
                 * if the actual image url works, we can use this
                // val url= "${BuildConfig.BASE_URL}${recipe.dynamicThumbnail}"
                //LoadImage(modifier = Modifier, url = url)
                 */
                LoadImage(modifier = Modifier, index = recipeId)
            }
            item {
                PrepInfoView(modifier = Modifier.padding(top = 32.dp), details = it.recipeDetails)
            }
            item {
                IngredientsView(modifier = Modifier, ingredients = it.ingredients)
            }
            item {
                Spacer(Modifier.height(120.dp))
            }
        }
    }
}

@Composable
fun PrepInfoView(modifier: Modifier = Modifier, details: RecipeDetailsData) {
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = light_black)
        )
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            PrepDataView(Modifier.weight(1f), details.amountLabel, details.amountNumber.toString())
            Spacer(
                modifier = Modifier
                    .height(48.dp)
                    .width(1.dp)
                    .background(light_black)
            )
            PrepDataView(Modifier.weight(1f), details.prepLabel, details.prepTime)
            Spacer(
                modifier = Modifier
                    .height(48.dp)
                    .width(1.dp)
                    .background(light_black)
            )
            PrepDataView(Modifier.weight(1f), details.cookingLabel, details.cookingTime)
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = light_black)
        )
    }
}

@Composable
fun IngredientsView(modifier: Modifier = Modifier, ingredients: List<IngredientData>) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp), horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = stringResource(R.string.ingredients),
            style = Typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        ingredients.forEach {
            IngredientView(modifier = Modifier, ingredient = it)
        }
    }
}

@Composable
fun IngredientView(modifier: Modifier = Modifier, ingredient: IngredientData) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = ingredient.ingredient,
            style = Typography.labelLarge.copy(fontWeight = FontWeight.Light),
            color = Color.Black,
            textAlign = TextAlign.Start, maxLines = 2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIngredientView() {
    IngredientView(
        modifier = Modifier,
        ingredient = IngredientData("800g butternut pumpkin, peeled, seeded, finely chopped")
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewIngredientsView() {
    IngredientsView(
        modifier = Modifier,
        ingredients = listOf(
            IngredientData("800g butternut pumpkin, peeled, seeded, finely chopped"),
            IngredientData("250g smooth ricotta")
        )
    )
}

@Composable
fun PrepDataView(modifier: Modifier = Modifier, title: String, displayValue: String) {
    Column(
        modifier = modifier.padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = title,
            style = Typography.labelLarge.copy(fontWeight = FontWeight.Light),
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = displayValue,
            style = Typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewPrepDataView() {
    PrepDataView(modifier = Modifier, title = "Serves", displayValue = "8")
}

@Preview(showBackground = true)
@Composable
fun PreviewPrepInfoView() {
    PrepInfoView(
        modifier = Modifier,
        details = RecipeDetailsData(
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
    )
}


@Preview
@Composable
fun PreviewRecipeDetailsContent() {
    RecipeDetailsContent(
        recipe = RecipeData(
            "Title",
            "Description",
            "",
            "",
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
        ),
        recipeId = 0
    )
}