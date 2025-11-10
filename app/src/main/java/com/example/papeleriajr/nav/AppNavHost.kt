package com.example.papeleriajr.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.papeleriajr.ui.screens.*
import com.example.papeleriajr.data.model.local.DemoData

@Composable
fun AppNavHost(nav: NavHostController) {
    NavHost(navController = nav, startDestination = NavRoute.Home.route) {

        composable(NavRoute.Home.route) {
            HomeScreen(
                onSeeCatalog = { nav.navigate(NavRoute.Explore.route) }
            )
        }

        composable(NavRoute.Explore.route) {
            ExploreScreen(
                onOpenCategory = { cat -> nav.navigate(NavRoute.Category.build(cat.id)) },
                onBack = { nav.popBackStack() }
            )
        }

        composable(NavRoute.Catalog.route) {
            CatalogScreen(
                onBack = { nav.popBackStack() },
                onOpenCheckout = { p -> nav.navigate(NavRoute.Checkout.build(p.id)) }
            )
        }

        composable(
            route = NavRoute.Category.route,
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments?.getString("categoryId") ?: ""
            val category = DemoData.categories.first { it.id == id }
            CategoryScreen(
                category = category,
                onBack = { nav.popBackStack() },
                onOpenCheckout = { pid -> nav.navigate(NavRoute.Checkout.build(pid)) }
            )
        }

        composable(
            route = NavRoute.Checkout.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStack ->
            val pid = backStack.arguments?.getString("productId") ?: ""
            CheckoutScreen(
                productId = pid,
                onBack = { nav.popBackStack() }
            )
        }
    }
}
