package com.example.papeleriajr.data.model

data class Product(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val imageUrl: String? = null,
    val categoryId: String = "",
    val checkoutUrl: String? = null   // <--- NUEVO: link a tu pasarela
)
