package com.mls.kmp.mor.nytnewskmp.ui

import cafe.adriel.voyager.core.model.StateScreenModel
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.update

private const val TAG = "HomeScreenViewModel"

class HomeScreenViewModel(
    logger: Logger
) : StateScreenModel<HomeScreenState>(HomeScreenState.Loading) {

    private val log = logger.withTag(TAG)

    init {
        loadData()
    }

    private fun loadData() {
        log.d { "loadData() called" }
        mutableState.update { HomeScreenState.Success(HelloWorldUiModel("Hello World!", 0)) }
    }

    fun onButtonClick() {
        mutableState.update { state: HomeScreenState ->
            when (state) {
                is HomeScreenState.Success -> state.copy(data = state.data.copy(number = state.data.number + 1))
                else -> HomeScreenState.Loading
            }
        }
    }
}

sealed class HomeScreenState {
    object Loading : HomeScreenState()
    object Error : HomeScreenState()
    data class Success(val data: HelloWorldUiModel) : HomeScreenState()
}

