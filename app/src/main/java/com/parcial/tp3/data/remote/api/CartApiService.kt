package com.parcial.tp3.data.remote.api

import com.parcial.tp3.data.remote.dto.CartAddRequestDto
import com.parcial.tp3.data.remote.dto.CartDto
import com.parcial.tp3.data.remote.dto.CartUpdateRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CartApiService {

    @GET("carts/{userId}")
    suspend fun getCartByUserId(
        @Path("userId") userId: Int
    ): CartDto

    @POST("carts/add")
    suspend fun createCart(
        @Body request: CartAddRequestDto
    ): CartDto

    @PUT("carts/{cartId}")
    suspend fun updateCart(
        @Path("cartId") cartId: Int,
        @Body request: CartUpdateRequestDto
    ): CartDto

}