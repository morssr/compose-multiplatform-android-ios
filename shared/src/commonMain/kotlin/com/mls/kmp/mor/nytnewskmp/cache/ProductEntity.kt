package com.mls.kmp.mor.nytnewskmp.cache

import database.Products

data class ProductEntity(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>,
)

fun ProductEntity.toProductDbObject(): Products {
    return Products(
        id = id.toLong(),
        title = title,
        description = description,
        price = price.toLong(),
        discount_precentage = discountPercentage,
        rating = rating,
        stock = stock.toLong(),
        brand = brand,
        category = category,
        thumbnail = thumbnail,
        images = images,
    )
}

fun Products.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id.toInt(),
        title = title,
        description = description,
        price = price.toInt(),
        discountPercentage = discount_precentage,
        rating = rating,
        stock = stock.toInt(),
        brand = brand,
        category = category,
        thumbnail = thumbnail,
        images = images,
    )
}
