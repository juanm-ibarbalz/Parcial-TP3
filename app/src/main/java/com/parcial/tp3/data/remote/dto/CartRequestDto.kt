package com.parcial.tp3.data.remote.dto

data class CartAddRequestDto(
    val userId: Int,
    val products: List<CartProductRequestDto>
)

data class CartUpdateRequestDto(
    val products: List<CartProductRequestDto>
)

data class CartProductRequestDto(
    val id: Int,
    val quantity: Int
)

