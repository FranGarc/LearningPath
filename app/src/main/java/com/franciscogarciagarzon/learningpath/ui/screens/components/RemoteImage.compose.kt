package com.franciscogarciagarzon.learningpath.ui.screens.components


import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import com.franciscogarciagarzon.learningpath.R
import com.franciscogarciagarzon.learningpath.domain.model.URL

@Composable

fun RemoteImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    @DrawableRes placeholderResource: Int,
    @DrawableRes errorResource: Int,
    contentDescription: String,
    contentScale: ContentScale = ContentScale.Fit,
) {
    Log.d("RemoteImage", "url: $imageUrl")
    if (imageUrl.endsWith("png")) {

        RemoteImagePng(
            imageUrl = imageUrl,
            placeholderResource = placeholderResource,
            errorResource = errorResource,
            modifier = modifier,
            contentDescription = contentDescription
        )

    } else {
        RemoteVector(
            spriteUrl = imageUrl,
            placeholderResource = placeholderResource,
            errorResource = errorResource,
            modifier = modifier,
            contentDescription = contentDescription
        )
    }


}

@Composable
fun RemoteVectorWithFallback(
    primaryUrl: URL,
    fallbackUrl: URL,
    @DrawableRes errorResource: Int,
    @DrawableRes placeholderResource: Int,
    modifier: Modifier,
    contentDescription: String
) {
    Log.i("RemoteVectorWithFallback", "primaryUrl: $primaryUrl ")
    Log.i("RemoteVectorWithFallback", "fallbackUrl: $fallbackUrl ")
    val spriteUrl = remember { mutableStateOf<String>(primaryUrl) }
    val localImagePainterUrl = remember { mutableStateOf<String?>(null) }
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(
            data = localImagePainterUrl.value
                ?: primaryUrl
        ).apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {
            placeholder(placeholderResource)
        }).build()
    )
    val isError = painter.state is AsyncImagePainter.State.Error

    LaunchedEffect(isError) {
        Log.d("RemoteVectorWithFallback", "LaunchedEffect: $isError ")

        if (isError)
            spriteUrl.value = fallbackUrl

    }
    Log.d("RemoteVectorWithFallback", "pre Image ${painter.request.data}")

    Image(
        painter = painter,
        contentDescription = contentDescription,
    )
}

@Composable
fun RemoteVector(
    spriteUrl: URL,
    @DrawableRes errorResource: Int,
    @DrawableRes placeholderResource: Int,
    modifier: Modifier,
    contentDescription: String
) {

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(spriteUrl.ifEmpty { placeholderResource })
            .size(Size.ORIGINAL)
            .scale(Scale.FIT)
            .crossfade(true)
            .placeholder(placeholderResource)
            .error(errorResource)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        modifier = modifier
            .fillMaxWidth(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Error -> {
                Log.e("RemoteVector", "AsyncImagePainter.State.Error: $spriteUrl ")
                Image(
                    painter = painter,
                    contentDescription = "Error retrieving image for $contentDescription",
                )
            }

            is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
            is AsyncImagePainter.State.Empty -> {}
            is AsyncImagePainter.State.Success -> Image(
                painter = painter,
                contentDescription = contentDescription,
            )
        }

    }

}

@Composable
fun RemoteImagePng(
    imageUrl: String,
    @DrawableRes errorResource: Int,
    @DrawableRes placeholderResource: Int,
    contentDescription: String,
    modifier: Modifier
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
        painter.value as AsyncImagePainter,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxSize()
    )
}

@Preview
@Composable
fun PreviewRemoteImagePng() {
    RemoteImage(
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/12.png",
        placeholderResource = R.drawable.ic_pokeball_icon,
        errorResource = R.drawable.ic_pokeball_icon,
        contentDescription = ""
    )
}

@Preview
@Composable
fun PreviewRemoteImageSvg() {
    RemoteImage(
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg",
        placeholderResource = R.drawable.ic_pokeball_icon,
        errorResource = R.drawable.ic_pokeball_icon,
        contentDescription = ""
    )
}