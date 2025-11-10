package com.example.papeleriajr.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.papeleriajr.data.model.local.DemoData

@Composable
fun CheckoutScreen(
    productId: String,
    onBack: () -> Unit
) {
    val ctx = LocalContext.current
    val product = DemoData.products.firstOrNull { it.id == productId }

    JRScaffold(
        titleLeftJR = true,
        showBack = true,
        onBackClick = onBack
    ) {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            if (product != null) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxWidth().height(180.dp)
                )
                Spacer(Modifier.height(12.dp))
                Text(product.name)
                Text("Precio: $${"%,.0f".format(product.price)}")
                Spacer(Modifier.height(16.dp))
                PrimaryButtonBlue(
                    text = "Pagar ahora",
                    onClick = {
                        // Aquí va tu URL real de pago (Stripe, MercadoPago, etc.)
                        val url = "https://buy.stripe.com/test_example?item=${product.id}"
                        ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    }
                )
            } else {
                Text("Producto no encontrado")
            }
        }
    }
}
