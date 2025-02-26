package com.coles.recipes.presentaion.recipes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coles.recipes.ui.theme.Typography
import com.coles.recipes.ui.theme.transparent_white

@Composable
fun PrepInfoChip(modifier: Modifier = Modifier, title: String, displayValue: String) {
    Row(
        modifier = modifier
            .background(shape = RoundedCornerShape(16.dp), color = transparent_white)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = title,
            style = Typography.labelLarge.copy(fontWeight = FontWeight.Light),
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(start = 8.dp, end = 4.dp),
            text = displayValue,
            style = Typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewPrepInfoChip() {
    PrepInfoChip(modifier = Modifier, title = "Serves", displayValue = "8")
}