package com.mls.kmp.mor.nytnewskmp.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mls.kmp.mor.nytnewskmp.ui.ProductPriceRow
import com.mls.kmp.mor.nytnewskmp.ui.theme.MyAppTheme

@Preview
@Composable
fun ProductScreenPreview() {
    MyAppTheme {
    }
}

@Preview(showBackground = true)
@Composable
fun ProductPriceRowPreview() {
    MyAppTheme {
        ProductPriceRow(
            price = 100,
            discountPercentage = 0.5,
            234
        )
    }
}