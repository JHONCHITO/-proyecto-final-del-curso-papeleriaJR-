package com.example.papeleriajr

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.papeleriajr.nav.AppNavHost

@Composable
fun JRApp() {
    val nav = rememberNavController()
    AppNavHost(nav)
}
