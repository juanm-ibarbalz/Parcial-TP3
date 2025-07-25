package com.parcial.tp3.shared

import com.parcial.tp3.domain.model.Product

interface IProductService {
    suspend fun getAll(limit: Int, skip: Int): List<Product>
    suspend fun getById(id: Int): Product
    suspend fun getByCategory(category: String, limit: Int, skip: Int): List<Product>
    suspend fun search(query: String): List<Product>
}