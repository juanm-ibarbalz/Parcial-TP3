package com.parcial.tp3.shared

import com.parcial.tp3.domain.model.ProductPreview
import kotlinx.coroutines.flow.Flow

interface IFavouriteService {
    suspend fun addToFavourites(product: ProductPreview)
    suspend fun removeFromFavourites(product: ProductPreview)
    suspend fun isFavourite(productId: Int): Boolean
    fun getAllFavourites(): Flow<List<ProductPreview>>
}
