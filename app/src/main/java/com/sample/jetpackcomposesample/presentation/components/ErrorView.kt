package com.sample.jetpackcomposesample.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sample.jetpackcomposesample.R
import com.sample.jetpackcomposesample.presentation.Dimens.MediumPadding2

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    retryClick: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.busy_animation))

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 8.dp)

    ) {
        LottieAnimation(
            modifier = modifier,
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )

        Text(
            modifier = Modifier.padding(start = MediumPadding2, end = MediumPadding2, top = MediumPadding2),
            text = stringResource(id = R.string.something_went_wrong),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.padding(horizontal = MediumPadding2),
            text = stringResource(id = R.string.an_alien_probably_blocking_your_signal),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        AppButton(
            modifier = modifier.fillMaxWidth().padding(MediumPadding2),
            text = stringResource(id = R.string.retry),
            onClick = retryClick
        )
    }


}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ErrorViewPreview() {
    ErrorView(retryClick = {})
}