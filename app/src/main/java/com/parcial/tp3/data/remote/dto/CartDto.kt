package com.parcial.tp3.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CartDto(
    val id: Int,
    @SerializedName("userId") val userId: Int,
    val products: List<CartItemDto>,
    val total: Double,
    val discountedTotal: Double,
    val totalProducts: Int,
    val totalQuantity: Int
)
