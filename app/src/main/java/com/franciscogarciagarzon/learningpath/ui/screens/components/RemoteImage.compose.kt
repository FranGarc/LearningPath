package com.franciscogarciagarzon.learningpath.ui.screens.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size

@Composable
fun RemoteImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    @DrawableRes placeholderResource: Int,
    @DrawableRes errorResource: Int,
    contentDescription: String,
    contentScale: ContentScale = ContentScale.Fit,

    ) {
    val painter = remember {
        mutableStateOf<AsyncImagePainter?>(null)
    }

    painter.value = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl.ifEmpty { errorResource })
            .crossfade(true)
            .placeholder(placeholderResource)
            .size(Size.ORIGINAL)
            .scale(Scale.FIT)
            .build()
    )

    Image(
        painter = painter.value as AsyncImagePainter,
        contentDescription = contentDescription,
        contentScale = ContentScale.FillWidth,
        modifier = modifier
//            .aspectRatio(1f)
            .fillMaxWidth()
//            .background(color = MaterialTheme.colorScheme.primary)
    )
}