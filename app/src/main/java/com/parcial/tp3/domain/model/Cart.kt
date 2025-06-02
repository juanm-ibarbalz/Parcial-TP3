package com.parcial.tp3.domain.model

data class Cart(
    val id: Int,
    val userId: Int,
    val products: List<CartItem>,
    val total: Double,
    val totalProducts: Int,
    val totalQuantity: Int
)
