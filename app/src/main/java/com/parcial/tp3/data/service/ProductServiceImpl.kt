package com.parcial.tp3.data.service

import com.parcial.tp3.data.remote.api.ProductApiService
import com.parcial.tp3.data.mappers.toDomain
import com.parcial.tp3.domain.model.Product
import com.parcial.tp3.shared.IProductService
import javax.inject.Inject

class ProductServiceImpl @Inject constructor(
    private val api: ProductApiService
) : IProductService {

    override suspend fun getAll(limit: Int, skip: Int): List<Product> {
        return api.getProducts(limit, skip).products.map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Product {
        return api.getProductById(id).toDomain()
    }
}
