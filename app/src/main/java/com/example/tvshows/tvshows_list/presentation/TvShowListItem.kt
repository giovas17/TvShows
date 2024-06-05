package com.example.tvshows.tvshows_list.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tvshows.R
import com.example.tvshows.presentation.common.AppImage
import com.example.tvshows.tvshows_list.data.TvShow
import com.example.tvshows.ui.theme.TvShowsTheme

const val TEXT_NAME_FRACTION_WIDTH = 0.8f
const val IMAGE_LOGO_SCALE = 9f / 12f
const val IMAGE_ICON_NEXT_FRACTION_WIDTH = 0.07f
const val SPACER_FRACTION_WIDTH = 0.01f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvShowListItem(tvShow: TvShow, onTvShowSelected: (tvShow: TvShow) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(ratio = 9f / 14f),
        shape = TvShowsTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        onClick = { onTvShowSelected(tvShow) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ratio = IMAGE_LOGO_SCALE)) {
                AppImage(
                    image = tvShow.image.medium,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize(),
                    contextDescription = null,
                )
            }
            Row(
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(TEXT_NAME_FRACTION_WIDTH)
                        .padding(6.dp)
                        .wrapContentHeight(),
                    text = tvShow.name,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_nav_right),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    colorFilter = ColorFilter.tint( MaterialTheme.colorScheme.onSecondaryContainer),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(IMAGE_ICON_NEXT_FRACTION_WIDTH)
                        .padding(top = 12.dp, bottom = 12.dp),
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .weight(SPACER_FRACTION_WIDTH))
            }
        }
    }
}

@Preview
@Composable
fun PreviewItemList() {
    TvShowListItem(tvShow = TvShow(name = "Test 1")) {}
}