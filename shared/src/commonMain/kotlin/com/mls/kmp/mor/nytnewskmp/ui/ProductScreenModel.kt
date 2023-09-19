package com.mls.kmp.mor.nytnewskmp.ui

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import co.touchlab.kermit.Logger
import com.mls.kmp.mor.nytnewskmp.data.ProductsRepository
import kotlinx.coroutines.launch

private const val TAG = "ProductScreenModel"

class ProductScreenModel(
    private val productId: Int,
    private val repository: ProductsRepository,
    logger: Logger
) : StateScreenModel<ProductScreenState>(ProductScreenState.Loading) {

    private val log = logger.withTag(TAG)

    init {
        coroutineScope.launch {
            try {
                val product = repository.getProduct(productId).toProductUiModel()
                updateState(ProductScreenState.Success(product))
            } catch (e: Exception) {
                log.e { "getProduct() failed to fetch product from API. error: ${e.message}" }
                updateState(ProductScreenState.Error(e))
            }
        }
    }

    private fun updateState(productScreenState: ProductScreenState) {
        mutableState.value = productScreenState
    }
}

sealed class ProductScreenState {
    object Loading : ProductScreenState()
    data class Success(val product: ProductUiModel) : ProductScreenState()
    data class Error(val error: Throwable) : ProductScreenState()
}
