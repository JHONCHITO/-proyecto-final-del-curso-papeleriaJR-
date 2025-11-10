package com.example.papeleriajr.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.papeleriajr.data.model.Category
import com.example.papeleriajr.data.model.local.DemoData

@Composable
fun CategoryScreen(
    category: Category,
    onBack: () -> Unit,
    onOpenCheckout: (productId: String) -> Unit
) {
    JRScaffold(
        titleLeftJR = true,
        showBack = true,
        onBackClick = onBack
    ) {
        Column(Modifier.fillMaxSize()) {
            AsyncImage(
                model = category.imageUrl,
                contentDescription = category.name,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(140.dp)
            )

            Text(
                text = category.name.replaceFirstChar { it.uppercase() },
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )

            val items = DemoData.allProductsFor(category.id)

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items) { p ->
                    ProductCardMini(p = p) { onOpenCheckout(p.id) }
                }
            }
        }
    }
}
