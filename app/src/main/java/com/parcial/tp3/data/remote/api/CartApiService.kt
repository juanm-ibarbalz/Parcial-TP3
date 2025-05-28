package com.parcial.tp3.data.remote.api

import com.parcial.tp3.data.remote.dto.CartDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CartApiService {

    @GET("carts/{userId}")
    suspend fun getCartByUserId(
        @Path("userId") userId: Int
    ): CartDto
}