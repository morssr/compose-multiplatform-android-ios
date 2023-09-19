package com.mls.kmp.mor.nytnewskmp.data

import co.touchlab.kermit.Logger
import com.mls.kmp.mor.nytnewskmp.api.ApiResponse
import com.mls.kmp.mor.nytnewskmp.api.ProductsApi
import com.mls.kmp.mor.nytnewskmp.api.toProductEntity
import com.mls.kmp.mor.nytnewskmp.cache.ProductsDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

private const val TAG = "ProductsRepositoryImpl"

class ProductsRepositoryImpl(
    private val productsApi: ProductsApi,
    private val productsDao: ProductsDao,
    logger: Logger
) : ProductsRepository {

    private val log = logger.withTag(TAG)

    override suspend fun getProducts(): List<Product> {
        log.v { "getProducts() called" }
        when (val products = productsApi.getProducts()) {
            is ApiResponse.Success -> {
                log.v { "getProducts() success" }
                productsDao.insertOrReplaceProducts(products.data.map { it.toProductEntity() })
            }
            is ApiResponse.Failure -> {
                log.e { "getProducts() failed to fetch products from API. error: ${products.error}" }
            }
        }
        return productsDao.getProducts().map { it.toProduct() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getProductsStream(): Flow<List<Product>> {
        log.v { "getProductsStream() called" }
        return flow<List<Product>> {
            when (val products = productsApi.getProducts()) {
                is ApiResponse.Success -> {
                    log.v { "getProducts() success" }
                    productsDao.insertOrReplaceProducts(products.data.map { it.toProductEntity() })
                }

                is ApiResponse.Failure -> {
                    log.e { "getProducts() failed to fetch products from API. error: ${products.error}" }
                }
            }
        }.flatMapLatest {
            productsDao.getProductsStream().map { it.toProducts() }
        }
    }

    override suspend fun getProduct(id: Int): Product {
        log.v { "getProduct() called" }
        return productsDao.getProduct(id).toProduct()
    }

    override fun getProductStream(id: Int): Flow<Product> {
        log.v { "getProductStream() called" }
        return productsDao.getProductStream(id).map { it.toProduct() }
    }
}