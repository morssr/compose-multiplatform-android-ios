@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.mls.kmp.mor.nytnewskmp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mls.kmp.mor.nytnewskmp.ui.common.HorizontalDotsIndicator
import com.mls.kmp.mor.nytnewskmp.ui.common.ProductImage
import com.mls.kmp.mor.nytnewskmp.ui.common.ProductScreenError
import com.mls.kmp.mor.nytnewskmp.ui.common.ProductScreenLoading
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import kotlin.math.roundToInt

data class ProductScreenRoute(val productId: Int) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: ProductScreenModel = koinInject(parameters = { parametersOf(productId) })
        val state by viewModel.state.collectAsState()

        ProductScreen(
            state,
            onBackClick = { navigator.pop() }
        )
    }
}

@Composable
fun ProductScreen(
    state: ProductScreenState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    when (state) {
        is ProductScreenState.Loading -> {
            ProductScreenLoading()
        }

        is ProductScreenState.Success -> {
            ProductScreenContent(
                product = state.product,
                modifier = modifier,
                onBackClick = onBackClick
            )
        }

        is ProductScreenState.Error -> {
            ProductScreenError()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProductScreenContent(
    product: ProductUiModel,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        product.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "navigate back"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                val pagerState = rememberPagerState { product.images.size }

                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(16.dp),
                    pageSpacing = 16.dp,
                ) {
                    ProductImage(
                        url = product.images[it],
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }

                HorizontalDotsIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    itemsCount = product.images.size,
                    currentItem = pagerState.currentPage
                )

                Spacer(modifier = Modifier.height(8.dp))

                ProductPriceRow(
                    price = product.price,
                    discountPercentage = product.discountPercentage,
                    stock = product.stock,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextWithLeadingIcon(
                    icon = { Icon(imageVector = Icons.Rounded.Star, "") },
                    text = product.rating.toString(),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    )
}

@Composable
fun ProductPriceRow(
    price: Int,
    discountPercentage: Double,
    stock: Int,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart),
//            verticalAlignment = Alignment.Bottom
        ) {

            val oldPrice: Int by remember { derivedStateOf { (price + (discountPercentage / 100 * price).roundToInt()) } }

            Text(
                text = "$$price",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.alignByBaseline()
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "$$oldPrice",
                style = MaterialTheme.typography.titleSmall,
                textDecoration = TextDecoration.LineThrough,
                fontWeight = FontWeight.Light,
                modifier = Modifier.alignByBaseline()
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "$discountPercentage% OFF",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alignByBaseline()
            )
        }

        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$stock left",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.alignByBaseline()
            )
        }
    }
}