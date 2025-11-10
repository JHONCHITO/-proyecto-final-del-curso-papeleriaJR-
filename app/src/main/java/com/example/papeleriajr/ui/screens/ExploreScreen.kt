package com.example.papeleriajr.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.papeleriajr.data.model.Category
import com.example.papeleriajr.data.model.local.DemoData

@Composable
fun ExploreScreen(
    onOpenCategory: (Category) -> Unit,
    onBack: () -> Unit
) {
    JRScaffold(
        titleLeftJR = true,
        showBack = true,
        onBackClick = onBack
    ) {
        Column(Modifier.fillMaxSize()) {
            var q by remember { mutableStateOf("") }
            OutlinedTextField(
                value = q,
                onValueChange = { q = it },
                leadingIcon = { Icon(Icons.Outlined.Search, null, tint = Color.Black) },
                placeholder = { Text("Buscar") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(8.dp))

            AsyncImage(
                model = DemoData.banner,
                contentDescription = "banner",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(140.dp)
            )

            Spacer(Modifier.height(8.dp))
            Text(
                "Tenemos todo lo que necesita para el regreso a clases...",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(8.dp))

            val filtered = DemoData.categories.filter { it.name.contains(q, true) }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filtered) { cat ->
                    PrimaryButtonBlue(
                        text = cat.name,
                        onClick = { onOpenCategory(cat) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

