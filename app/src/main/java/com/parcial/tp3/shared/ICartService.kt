package com.parcial.tp3.shared

import com.parcial.tp3.domain.model.Cart

interface ICartService {
    suspend fun getCartByUserId(userId: Int): Cart
}
