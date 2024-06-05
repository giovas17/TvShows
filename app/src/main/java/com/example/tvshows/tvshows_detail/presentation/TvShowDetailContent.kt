package com.example.tvshows.tvshows_detail.presentation

import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.example.tvshows.R
import com.example.tvshows.presentation.common.AppImage
import com.example.tvshows.tvshows_list.data.TvShow

@Composable
fun TvShowDetailContent(tvShow: TvShow) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AppImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                image = tvShow.image.medium,
                contentScale = ContentScale.FillWidth,
                contextDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(3f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = tvShow.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = stringResource(
                        id = R.string.tv_shows_detail_screen_language, tvShow.language
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = stringResource(
                        id = R.string.tv_shows_detail_screen_genres, tvShow.genres.joinToString()
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = stringResource(
                        id = R.string.tv_shows_detail_screen_premier, tvShow.premiered
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = stringResource(
                        id = R.string.tv_shows_detail_screen_rating, tvShow.rating.average
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        val fontSize = MaterialTheme.typography.bodyLarge.fontSize.value
        val color = MaterialTheme.colorScheme.onPrimaryContainer.toArgb()
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            factory = { context -> TextView(context) },
            update = {
                it.text = HtmlCompat.fromHtml(tvShow.summary, HtmlCompat.FROM_HTML_MODE_COMPACT)
                it.textSize = fontSize
                it.setTextColor(color)
            }
        )
        Text(
            text = stringResource(
                id = R.string.tv_shows_detail_screen_prev_episode,
                tvShow._links.previousepisode.name
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview
@Composable
fun PreviewTevShowDetailContent() {
    TvShowDetailContent(
        tvShow = TvShow(
            name = "Test 1",
            genres = listOf("Drama", "Horror", "Action")
        )
    )
}