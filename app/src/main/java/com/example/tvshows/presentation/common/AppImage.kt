package com.example.tvshows.presentation.common

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.tvshows.R

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    image: Any?,
    contentScale: ContentScale,
    contextDescription: String?,
    @DrawableRes placeholder: Int? = null,
) {
    var currentScale by remember { mutableStateOf(contentScale) }
    val fallback = placeholder ?: R.drawable.empty_logo
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .crossfade(true)
            .decoderFactory(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoderDecoder.Factory()
                } else {
                    GifDecoder.Factory()
                },
            )
            .data(image)
            .build(),
        contentDescription = contextDescription
            ?: stringResource(id = R.string.item_grid_description_image),
        modifier = modifier,
        contentScale = currentScale,
        placeholder = painterResource(id = fallback),
        error = painterResource(id = fallback),
        fallback = painterResource(id = fallback),
        onSuccess = {
            currentScale = contentScale
        },
        onError = {
            currentScale = ContentScale.Fit
        },
    )
}

@Preview
@Composable
fun PreviewAppImage() {
    AppImage(image = "", contentScale = ContentScale.Fit, contextDescription = null)
}