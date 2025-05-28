package com.parcial.tp3.data.local.service

import com.parcial.tp3.data.local.dao.FavouriteDao
import com.parcial.tp3.data.mappers.toDomain
import com.parcial.tp3.data.mappers.toEntity
import com.parcial.tp3.domain.model.ProductPreview
import com.parcial.tp3.shared.IFavouriteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteServiceImpl @Inject constructor(
    private val dao: FavouriteDao
) : IFavouriteService {

    override suspend fun addToFavourites(product: ProductPreview) {
        dao.insert(product.toEntity())
    }

    override suspend fun removeFromFavourites(product: ProductPreview) {
        dao.delete(product.toEntity())
    }

    override suspend fun isFavourite(productId: Int): Boolean {
        return dao.isFavourite(productId)
    }

    override fun getAllFavourites(): Flow<List<ProductPreview>> {
        return dao.getAllFavourites().map { entities ->
            entities.map { it.toDomain() }
        }
    }
}
