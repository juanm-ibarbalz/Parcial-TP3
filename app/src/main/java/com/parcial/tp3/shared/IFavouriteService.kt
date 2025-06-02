package com.parcial.tp3.shared

import com.parcial.tp3.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IFavouriteService {
    suspend fun addToFavourites(product: Product)
    suspend fun removeFromFavourites(product: Product)
    suspend fun isFavourite(productId: Int): Boolean
    fun getAllFavourites(): Flow<List<Product>>
}
