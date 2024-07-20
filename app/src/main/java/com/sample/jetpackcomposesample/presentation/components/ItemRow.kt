package com.sample.jetpackcomposesample.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sample.jetpackcomposesample.R
import com.sample.jetpackcomposesample.data.model.RepositoriesResponse
import com.sample.jetpackcomposesample.presentation.Dimens.ExtraSmallPadding
import com.sample.jetpackcomposesample.presentation.Dimens.IconSize
import com.sample.jetpackcomposesample.presentation.Dimens.SmallIconSize

@Composable
fun ItemRow(item: RepositoriesResponse.Item) {
    var showDetails by remember { mutableStateOf(false) }
    var rotateDegree by remember { mutableFloatStateOf(0f) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.owner.avatarUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(IconSize)
            )


            Column(modifier = Modifier.padding(start = 8.dp).weight(1f)) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp), text = item.fullName,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )


            }

            IconButton(onClick = {
                rotateDegree = if (showDetails) {
                    0f
                } else {
                    180f
                }
                showDetails = !showDetails
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(SmallIconSize)
                        .rotate(rotateDegree)
                )
            }
        }

        AnimatedVisibility(visible = showDetails) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = item.description,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(ExtraSmallPadding)
                            .clip(CircleShape)
                            .background(Color.Blue)
                    )

                    Text(
                        modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                        text = item.language,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(SmallIconSize),
                    )

                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = item.forks.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

        }
        HorizontalDivider(
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.onPrimary,
            thickness = 1.dp
        )
    }


}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ItemRowPreview() {
    ItemRow(item = RepositoriesResponse.Item())
}