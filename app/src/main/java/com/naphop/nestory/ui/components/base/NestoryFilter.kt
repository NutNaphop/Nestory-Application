package com.naphop.nestory.ui.components.base

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naphop.nestory.ui.theme.NestoryTheme

@Composable
fun NestoryFilterRow(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(13.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(categories) { category ->
            val isSelected = category == selectedCategory
            NestoryFilterChip(
                title = category,
                isSelected = isSelected,
                onCategorySelected = onCategorySelected
            )
        }
    }
}

@Composable
fun NestoryFilterChip(
    title: String,
    isSelected: Boolean,
    onCategorySelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .clip(shape = RoundedCornerShape(30.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(30.dp)
            )
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
            )
            .clickable(
                onClick = {
                    onCategorySelected(title)
                }
            )
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 23.dp),
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NestoryFilterRowPreview() {
    val cat = listOf<String>(
        "All", "Food", "Medical"
    )
    val selectedCategory = cat.first()
    NestoryTheme() {
        Box(modifier = Modifier.padding(20.dp)) {
            NestoryFilterRow(
                categories = cat,
                selectedCategory = selectedCategory,
                onCategorySelected = {}
            )
        }

    }
}