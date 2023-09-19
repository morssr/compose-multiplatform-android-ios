package com.mls.kmp.mor.nytnewskmp.cache

import kotlinx.coroutines.flow.Flow

interface ProductsDao {
    suspend fun insertOrReplaceProducts(products: List<ProductEntity>)
    suspend fun getProducts(): List<ProductEntity>
    fun getProductsStream(): Flow<List<ProductEntity>>
    suspend fun getProduct(id: Int): ProductEntity
    fun getProductStream(id: Int): Flow<ProductEntity>
}