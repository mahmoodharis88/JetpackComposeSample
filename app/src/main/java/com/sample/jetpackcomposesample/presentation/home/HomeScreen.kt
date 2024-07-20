package com.sample.jetpackcomposesample.presentation.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sample.jetpackcomposesample.presentation.Dimens.MediumPadding1
import com.sample.jetpackcomposesample.presentation.components.CardShimmerEffect
import com.sample.jetpackcomposesample.presentation.components.ErrorView
import com.sample.jetpackcomposesample.presentation.components.ItemRow

@Composable
fun HomeScreen(state: HomeUiState, event: (HomeUiEvent) -> Unit) {

    when (state) {
        LoadingState -> {
            ShimmerEffect()
        }

        is ContentState -> {
            LazyColumn {
                items(state.response.items) {
                    ItemRow(item = it)
                }
            }
        }

        is ErrorState -> {
            ErrorView(retryClick = { event(RetryEvent) })
        }
    }

}


@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    HomeScreen(LoadingState) {}
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10) {
            CardShimmerEffect()
        }
    }
}
