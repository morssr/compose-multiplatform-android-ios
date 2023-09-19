package com.mls.kmp.mor.nytnewskmp.api

import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    val products: List<ProductResponse>,
    val total: Int,
    val skip: Int,
    val limit: Int
)