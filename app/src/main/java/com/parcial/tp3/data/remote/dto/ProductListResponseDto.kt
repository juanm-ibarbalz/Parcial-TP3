package com.parcial.tp3.data.remote.dto

data class ProductListResponseDto(
    val products: List<ProductDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
