package com.coles.recipes.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.coles.recipes.R

@Composable
fun LoadImage(modifier: Modifier = Modifier, index: Int) {
    val image = when (index) {
        0 -> R.drawable.i0
        1 -> R.drawable.i1
        2 -> R.drawable.i2
        3 -> R.drawable.i3
        4 -> R.drawable.i4
        5 -> R.drawable.i5
        6 -> R.drawable.i6
        7 -> R.drawable.i7
        else -> {
            R.drawable.i0
        }
    }
    Image(
        painter = painterResource(image),
        contentDescription = "Recipe Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}

@Composable
fun LoadImage(modifier: Modifier = Modifier, url: String) {
    val context = LocalContext.current

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = "Recipe Image",
        error = painterResource(R.drawable.ic_launcher_foreground),
        placeholder = painterResource(R.drawable.placeholder_icon),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}