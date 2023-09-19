package com.mls.kmp.mor.nytnewskmp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CurrencyBitcoin
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mls.kmp.mor.nytnewskmp.ui.common.ProductImage
import org.koin.compose.koinInject

class HomeScreenRoute : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: HomeScreenModel = koinInject()
        val state by viewModel.state.collectAsState()

        MainScreenContent(
            screenState = state,
            onProductClick = { product ->
                navigator.push(ProductScreenRoute(product.id))
            }
        )
    }
}

@Composable
fun MainScreenContent(
    screenState: HomeScreenState,
    modifier: Modifier = Modifier,
    onProductClick: (ProductUiModel) -> Unit = {}
) {
    Surface(modifier = modifier) {
        when (val state: HomeScreenState = screenState) {
            is HomeScreenState.Loading -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is HomeScreenState.Success -> {
                ProductsList(
                    products = state.products,
                    onProductClick = onProductClick
                )
            }

            is HomeScreenState.Error -> {
                Text(text = "Error")
            }
        }
    }
}

@Composable
private fun ProductsList(
    products: List<ProductUiModel>,
    modifier: Modifier = Modifier,
    onProductClick: (ProductUiModel) -> Unit = {}
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(156.dp),
        modifier = modifier.windowInsetsPadding(
            WindowInsets.safeDrawing
        ),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp,
    ) {
        items(items = products) { product ->
            ProductCell(product = product, onProductClick = onProductClick)
        }
    }
}

@Composable
private fun ProductCell(
    product: ProductUiModel,
    modifier: Modifier = Modifier,
    onProductClick: (ProductUiModel) -> Unit = {}
) {
    Card(colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = modifier
            .clickable { onProductClick(product) }) {

        Column(
            modifier = Modifier.background(
                Brush.linearGradient(
                    listOf(
                        Color.Transparent,
                        Color.LightGray.copy(alpha = 0.1f)
                    )
                )
            )
        ) {

            ProductImage(
                url = product.thumbnail,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(192.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                TextWithLeadingIcon(
                    icon = { Icon(imageVector = Icons.Rounded.CurrencyBitcoin, "") },
                    text = product.price.toString().plus(".99"),
                )

                TextWithLeadingIcon(
                    icon = { Icon(imageVector = Icons.Rounded.Star, "") },
                    text = product.rating.toString(),
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = product.title,
                style = MaterialTheme.typography.bodyMedium,
            )

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun TextWithLeadingIcon(
    icon: @Composable () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        icon()
        Spacer(modifier = Modifier.width(2.dp))
        Text(text = text)
    }
}
