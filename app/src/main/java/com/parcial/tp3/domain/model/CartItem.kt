package com.parcial.tp3.domain.model

data class CartItem(
    val productId: Int,
    val visualId: Int = 0,  // Visual ID para identificar las unidades visuales
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val thumbnail: String
)