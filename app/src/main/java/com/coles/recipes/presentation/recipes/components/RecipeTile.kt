package com.coles.recipes.presentation.recipes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coles.recipes.data.model.RecipeData
import com.coles.recipes.data.model.RecipeDetailsData
import com.coles.recipes.presentation.common.LoadImage
import com.coles.recipes.ui.theme.Typography
import com.coles.recipes.ui.theme.coles_red

@Composable
fun RecipeTile(
    modifier: Modifier = Modifier,
    recipe: RecipeData,
    index: Int,
    onRecipeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(bottom = 32.dp)
            .fillMaxWidth()
            .clickable {
                onRecipeClick.invoke()
            }
    ) {

        Card(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.White),
            shape = RoundedCornerShape(
                0
            )
        ) {
            Box(modifier = Modifier) {
                val url =
                    "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg?quality=90&resize=556,505"
                /**
                 * if the actual image url works, we can use this
                // val url= "${BuildConfig.BASE_URL}${recipe.dynamicThumbnail}"
                //LoadImage(modifier = Modifier, url = url)
                 */
                LoadImage(modifier = Modifier, index = index)
                Row(Modifier.align(Alignment.BottomEnd)) {
                    PrepInfoChip(
                        Modifier.padding(bottom = 8.dp, end = 8.dp),
                        recipe.recipeDetails.amountLabel,
                        recipe.recipeDetails.amountNumber.toString()
                    )
                    PrepInfoChip(
                        Modifier.padding(bottom = 8.dp, end = 8.dp),
                        recipe.recipeDetails.prepLabel,
                        recipe.recipeDetails.prepTime
                    )
                    PrepInfoChip(
                        Modifier.padding(bottom = 8.dp, end = 8.dp),
                        recipe.recipeDetails.cookingLabel,
                        recipe.recipeDetails.cookingTime
                    )
                }
            }
        }
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
            text = recipe.dynamicTitle,
            maxLines = 2,
            style = Typography.headlineSmall,
            color = coles_red
        )
        Text(
            text = recipe.dynamicThumbnailAlt,
            style = Typography.bodyMedium,
            color = Color.Black,
            maxLines = 2,
        )
    }
}


@Preview
@Composable
fun PreviewRecipeTile() {
    RecipeTile(
        recipe = RecipeData(
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
            ingredients = listOf()
        ),
        index = 1,
        onRecipeClick = {})
}