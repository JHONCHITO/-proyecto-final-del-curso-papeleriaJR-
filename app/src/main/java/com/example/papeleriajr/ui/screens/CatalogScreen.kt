package com.example.papeleriajr.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.papeleriajr.data.model.Product
import com.example.papeleriajr.data.model.local.DemoData

@Composable
fun CatalogScreen(
    onBack: () -> Unit,
    onOpenCheckout: (Product) -> Unit = {}
) {
    JRScaffold(
        titleLeftJR = true,
        showBack = true,
        onBackClick = onBack
    ) { _ ->

        var q by remember { mutableStateOf("") }

        Column(Modifier.fillMaxSize()) {
            OutlinedTextField(
                value = q,
                onValueChange = { q = it },
                leadingIcon = { Icon(Icons.Outlined.Search, null, tint = Color.Black) },
                placeholder = { Text("Buscar") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            val filtrados = remember(q) {
                if (q.isBlank()) DemoData.products
                else DemoData.products.filter {
                    it.name.contains(q, ignoreCase = true)
                }
            }

            SectionTitle("Productos de papelería")
            ProductGrid(
                items = filtrados.take(6),
                onClick = onOpenCheckout
            )

            Spacer(Modifier.height(8.dp))
            SectionTitle("Productos para artes")
            ProductGrid(
                items = DemoData.products.shuffled().take(6),
                onClick = onOpenCheckout
            )

            Spacer(Modifier.height(16.dp))
            FooterSocial()
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text,
        color = Color(0xFFF5C543),
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 8.dp)
    )
}

@Composable
private fun ProductGrid(
    items: List<Product>,
    onClick: (Product) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = true,
        modifier = Modifier.heightIn(min = 180.dp)
    ) {
        items(items) { p ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(Color(0xFF0BA59A))
            ) {
                AsyncImage(
                    model = p.imageUrl,
                    contentDescription = p.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(86.dp)
                        .background(Color.White)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "$${"%,.0f".format(p.price)}",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(4.dp))
                PrimaryButtonBlue(
                    text = p.name.split(" ").first(),
                    onClick = { onClick(p) }
                )
            }
        }
    }
}

@Composable
private fun FooterSocial() {
    val ctx = LocalContext.current

    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Text("SÍGUENOS", color = Color.White, fontWeight = FontWeight.SemiBold)
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            PrimaryButtonBlue("FB", onClick = {
                ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/")))
            })
            PrimaryButtonBlue("IG", onClick = {
                ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/")))
            })
            PrimaryButtonBlue("YT", onClick = {
                ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/")))
            })
            PrimaryButtonBlue("WA", onClick = {
                ctx.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://wa.me/573187092130?text=Hola%20Papeler%C3%ADa%20JR")
                    )
                )
            })
        }
        Spacer(Modifier.height(8.dp))
        Text(
            "RESERVE ATENCIÓN POR NUESTRO CHAT\n3187092130",
            color = Color(0xFFF5C543),
            fontWeight = FontWeight.SemiBold
        )
    }
}
