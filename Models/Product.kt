package com.buyansell.models

data class Product(
    val id: String,
    val name: String,
    val barcode: String,
    val purchasePrice: Double,
    val salePrice: Double,
    var quantity: Int,
    val imageUrl: String? = null,
    val minStock: Int = 5
)
