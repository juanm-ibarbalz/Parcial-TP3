package com.parcial.tp3.shared

import com.parcial.tp3.data.remote.dto.CartProductRequestDto
import com.parcial.tp3.domain.model.Cart

interface ICartService {
    suspend fun getCartByUserId(userId: Int): Cart
    suspend fun createCart(userId: Int, items: List<CartProductRequestDto>): Cart
    suspend fun updateCart(cartId: Int, items: List<CartProductRequestDto>): Cart
}
