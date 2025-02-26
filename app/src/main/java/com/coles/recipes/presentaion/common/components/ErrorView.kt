package com.coles.recipes.presentaion.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coles.recipes.R
import com.coles.recipes.ui.theme.Typography
import com.coles.recipes.ui.theme.coles_red

@Composable
fun LoadingErrorView(modifier: Modifier = Modifier, onRetry: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.something_went_wrong),
                style = Typography.bodyLarge,
                color = Color.Black,
                maxLines = 2,
            )
            Button(
                modifier = Modifier.padding(top = 24.dp),
                colors = ButtonDefaults.buttonColors().copy(containerColor = coles_red),
                onClick = { onRetry.invoke() }) {
                Text(
                    text = stringResource(R.string.retry),
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoadingErrorView() {
    LoadingErrorView(onRetry = {})
}