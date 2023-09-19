package com.mls.kmp.mor.nytnewskmp.ui

import com.mls.kmp.mor.nytnewskmp.data.Product

data class ProductUiModel(
    val id: Int = 0,
    val title: String = "title",
    val description: String = "description",
    val price: Int = 0,
    val discountPercentage: Double = 0.0,
    val rating: Double = 0.0,
    val stock: Int = 0,
    val brand: String = "brand",
    val category: String = "category",
    val thumbnail: String = "thumbnail",
    val images: List<String> = listOf(),
)

fun Product.toProductUiModel(): ProductUiModel {
    return ProductUiModel(
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

fun List<Product>.toProductUiModels(): List<ProductUiModel> {
    return map { it.toProductUiModel() }
}