package com.parcial.tp3.domain.model

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val fullName: String,
    val token: String,
    val image: String
)