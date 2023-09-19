package com.mls.kmp.mor.nytnewskmp.api

interface ProductsApi {
    suspend fun getProducts(): ApiResponse<List<ProductResponse>>
    suspend fun getProduct(id: Int): ApiResponse<ProductResponse>
}