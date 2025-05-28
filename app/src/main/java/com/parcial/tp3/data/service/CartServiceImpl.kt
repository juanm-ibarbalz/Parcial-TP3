package com.parcial.tp3.data.service

import com.parcial.tp3.data.remote.api.CartApiService
import com.parcial.tp3.data.mappers.toDomain
import com.parcial.tp3.domain.model.Cart
import com.parcial.tp3.shared.ICartService
import javax.inject.Inject

class CartServiceImpl @Inject constructor(
    private val api: CartApiService
) : ICartService {

    override suspend fun getCartByUserId(userId: Int): Cart {
        return api.getCartByUserId(userId).toDomain()
    }
}