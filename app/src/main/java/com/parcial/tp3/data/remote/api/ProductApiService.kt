package com.parcial.tp3.data.remote.api

import com.parcial.tp3.data.remote.dto.ProductListResponseDto
import com.parcial.tp3.data.remote.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {

    @GET("products")
    suspend fun getAllProducts(): ProductListResponseDto

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductDto
}
