package com.example.papeleriajr.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.papeleriajr.data.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JRScaffold(
    titleLeftJR: Boolean = true,
    showBell: Boolean = false,
    showBack: Boolean = false,
    onBackClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = if (showBack) onBackClick else onMenuClick) {
                        Icon(
                            imageVector = if (showBack) Icons.Filled.ArrowBack else Icons.Filled.Menu,
                            contentDescription = if (showBack) "atrás" else "menú",
                            tint = Color(0xFFF5C543)
                        )
                    }
                },
                title = {
                    if (titleLeftJR) {
                        Text(
                            "Papelería JR",
                            color = Color(0xFFF5C543),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            )
        },
        floatingActionButton = { WhatsAppFAB() }
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFF0BA59A))
                .padding(padding)
        ) {
            content(padding)
        }
    }
}

@Composable
fun YellowTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text,
        color = Color(0xFFF5C543),
        fontSize = 28.sp,
        lineHeight = 34.sp,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

/** FIRMA CORRECTA: onClick es el 2do parámetro */
@Composable
fun PrimaryButtonBlue(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF2F54EB))
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ProductCardMini(p: Product, onClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .widthIn(min = 120.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0BA59A))
            .padding(6.dp)
    ) {
        AsyncImage(
            model = p.imageUrl,
            contentDescription = p.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
        )
        Spacer(Modifier.height(6.dp))
        PrimaryButtonBlue(
            text = p.name.take(12),
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun WhatsAppFAB() {
    val ctx = LocalContext.current
    FloatingActionButton(
        onClick = {
            val url = "https://wa.me/573187092130?text=Hola%20Papeler%C3%ADa%20JR"
            ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        },
        containerColor = Color(0xFF25D366),
        contentColor = Color.White
    ) { Text("WA") }
}
