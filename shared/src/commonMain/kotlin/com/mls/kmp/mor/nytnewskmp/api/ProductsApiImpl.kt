package com.mls.kmp.mor.nytnewskmp.api

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val TAG = "ProductsApiImpl"

class ProductsApiImpl(private val client: HttpClient, logger: Logger) : ProductsApi {

    private val log = logger.withTag(TAG)

    override suspend fun getProducts(): ApiResponse<List<ProductResponse>> =
        try {
            log.v { "getProducts() called" }
            val response: ProductsResponse = client.get("https://dummyjson.com/products").body()
            log.v { "getProducts() response size: ${response.products.size}" }
            ApiResponse.Success(response.products)
        } catch (e: Exception) {
            log.e { "getProducts() error: ${e.message}" }
            ApiResponse.Failure(e)
        }

    override suspend fun getProduct(id: Int): ApiResponse<ProductResponse> =
        try {
            log.v { "getProduct() called with id: $id" }
            val response: ProductResponse = client.get("https://dummyjson.com/products/$id").body()
            log.v { "getProduct() response: $response" }
            ApiResponse.Success(response)
        } catch (e: Exception) {
            log.e { "getProduct() error: ${e.message}" }
            ApiResponse.Failure(e)
        }
}