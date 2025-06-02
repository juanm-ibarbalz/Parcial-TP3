package com.parcial.tp3.data.remote.dto

data class CartItemDto(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val thumbnail: String
)
