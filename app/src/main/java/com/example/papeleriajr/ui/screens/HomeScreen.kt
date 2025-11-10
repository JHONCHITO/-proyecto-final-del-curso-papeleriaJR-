package com.example.papeleriajr.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.papeleriajr.data.model.local.DemoData

@Composable
fun HomeScreen(
    onSeeCatalog: () -> Unit
) {
    JRScaffold(
        titleLeftJR = true,
        showBack = false
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(12.dp))
            AsyncImage(
                model = DemoData.banner,
                contentDescription = "banner",
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .height(140.dp)
            )
            Spacer(Modifier.height(12.dp))
            YellowTitle("Encuentra")
            Text(
                "todo lo que necesitas para el colegio.",
                color = Color(0xFFF5C543),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(16.dp))
            PrimaryButtonBlue(
                text = "ver catálogo de la papelería",
                onClick = onSeeCatalog
            )
        }
    }
}
