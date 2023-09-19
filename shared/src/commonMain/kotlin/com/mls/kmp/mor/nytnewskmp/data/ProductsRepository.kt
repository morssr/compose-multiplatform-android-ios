package com.mls.kmp.mor.nytnewskmp.data

import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(): List<Product>
    fun getProductsStream(): Flow<List<Product>>
    suspend fun getProduct(id: Int): Product
    fun getProductStream(id: Int): Flow<Product>
}