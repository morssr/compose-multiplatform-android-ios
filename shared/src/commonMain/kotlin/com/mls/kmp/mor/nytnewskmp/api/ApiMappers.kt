package com.mls.kmp.mor.nytnewskmp.api

import com.mls.kmp.mor.nytnewskmp.cache.ProductEntity

fun ProductResponse.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail,
        images = images,
    )
}

fun ProductsResponse.toProductEntities(): List<ProductEntity> {
    return products.map { it.toProductEntity() }
}