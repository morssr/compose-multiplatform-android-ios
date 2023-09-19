package com.mls.kmp.mor.nytnewskmp.cache

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import co.touchlab.kermit.Logger
import com.mls.kmp.mor.nytnewskmp.database.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

private const val TAG = "ProductsDaoImpl"

class ProductsDaoImpl(
    dbInstance: AppDatabase,
    private val coroutineContext: CoroutineContext,
    logger: Logger
) : ProductsDao {
    private val log = logger.withTag(TAG)
    private val productsDb = dbInstance.productsQueries

    override suspend fun insertOrReplaceProducts(products: List<ProductEntity>) {
        log.v { "insert or replace products called with products list size: ${products.size}" }
        productsDb.transaction {
            products.forEach { product ->
                productsDb.insertOrReplaceProduct(product.toProductDbObject())
            }
        }
    }

    override suspend fun getProducts(): List<ProductEntity> {
        log.v { "get products called" }
        return productsDb.getProductsList().executeAsList().map { it.toProductEntity() }
    }

    override fun getProductsStream(): Flow<List<ProductEntity>> {
        log.v { "get products stream called" }
        return productsDb.getProductsList().asFlow().mapToList(coroutineContext)
            .map { it.map { it.toProductEntity() } }
    }

    override suspend fun getProduct(id: Int): ProductEntity {
        log.v { "get product called with id: $id" }
        return productsDb.getProductById(id.toLong()).executeAsOne().toProductEntity()
    }

    override fun getProductStream(id: Int): Flow<ProductEntity> {
        log.v { "get product stream called with id: $id" }
        return productsDb.getProductById(id.toLong()).asFlow()
            .mapToOne(coroutineContext).map { it.toProductEntity() }
    }


}