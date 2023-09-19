package com.mls.kmp.mor.nytnewskmp.ui

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import co.touchlab.kermit.Logger
import com.mls.kmp.mor.nytnewskmp.data.ProductsRepository
import kotlinx.coroutines.launch

private const val TAG = "HomeScreenModel"

class HomeScreenModel(
    private val repository: ProductsRepository,
    logger: Logger
) : StateScreenModel<HomeScreenState>(HomeScreenState.Loading) {

    private val log = logger.withTag(TAG)

    init {
        coroutineScope.launch {
            try {
                val products = repository.getProducts().toProductUiModels()
                updateState(HomeScreenState.Success(products))
            } catch (e: Exception) {
                log.e { "getProducts() failed to fetch products from API. error: ${e.message}" }
                updateState(HomeScreenState.Error(e))
            }
        }
    }

    private fun updateState(homeScreenState: HomeScreenState) {
        mutableState.value = homeScreenState
    }
}

sealed class HomeScreenState {
    object Loading : HomeScreenState()
    data class Success(val products: List<ProductUiModel>) : HomeScreenState()
    data class Error(val error: Throwable) : HomeScreenState()
}