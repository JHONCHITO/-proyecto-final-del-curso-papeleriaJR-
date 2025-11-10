package com.example.papeleriajr.nav

sealed class NavRoute(val route: String) {
    data object Home : NavRoute("home")
    data object Explore : NavRoute("explore")
    data object Catalog : NavRoute("catalog")
    data object Category : NavRoute("category/{categoryId}") {
        fun build(categoryId: String) = "category/$categoryId"
    }
    data object Checkout : NavRoute("checkout/{productId}") {
        fun build(productId: String) = "checkout/$productId"
    }
}
