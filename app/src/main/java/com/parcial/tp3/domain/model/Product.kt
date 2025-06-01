package com.parcial.tp3.domain.model

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val category: String,
    val description: String
)
