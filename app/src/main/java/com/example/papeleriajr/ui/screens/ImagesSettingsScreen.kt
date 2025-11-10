package com.example.papeleriajr.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.papeleriajr.data.model.local.DemoData
import com.example.papeleriajr.data.prefs.AppImagesProvider
import kotlinx.coroutines.launch

@Composable
fun ImagesSettingsScreen(
    onBack: () -> Unit,
    onHomeClick: () -> Unit
) {
    val ctx = LocalContext.current
    val repo = remember { AppImagesProvider.get(ctx) }
    val scope = rememberCoroutineScope()

    val bannerPicker = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { scope.launch { repo.setBanner(it.toString()) } }
    }

    JRScaffold(
        titleLeftJR = false,
        showBack = true,
        onBackClick = onBack,
        onHomeClick = onHomeClick
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                val bannerOverride by repo.bannerFlow.collectAsState(initial = null)
                val bannerModel: Any = bannerOverride ?: DemoData.banner
                Text("Banner")
                Spacer(Modifier.height(8.dp))
                AsyncImage(
                    model = bannerModel,
                    contentDescription = "banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                )
                Spacer(Modifier.height(8.dp))
                PrimaryButtonBlue("Cambiar banner", onClick = { bannerPicker.launch("image/*") })
            }

            item { Spacer(Modifier.height(12.dp)) }

            items(DemoData.categories, key = { it.id }) { cat ->
                val override by remember(cat.id) { repo.categoryImageFlow(cat.id) }.collectAsState(initial = null)
                val model: Any = override ?: cat.imageUrl
                Text(cat.name.replaceFirstChar { it.uppercase() })
                Spacer(Modifier.height(4.dp))
                AsyncImage(
                    model = model,
                    contentDescription = cat.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
                Spacer(Modifier.height(6.dp))
                val picker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                    uri?.let { scope.launch { repo.setCategoryImage(cat.id, it.toString()) } }
                }
                PrimaryButtonBlue("Cambiar imagen de ${cat.name}", onClick = { picker.launch("image/*") })
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
