package com.franciscogarciagarzon.learningpath.ui.screens.components


import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
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

        val painter = remember {
            mutableStateOf<AsyncImagePainter?>(null)
        }
        painter.value = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl.ifEmpty { errorResource })
//            .decoderFactory(SvgDecoder.Factory())
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
//            .aspectRatio(1f)
                .fillMaxSize()
//            .background(color = MaterialTheme.colorScheme.primary)
        )
    } else {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl.ifEmpty { errorResource })
                .size(Size.ORIGINAL)
                .scale(Scale.FIT)
                .crossfade(true)
                .placeholder(placeholderResource)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            modifier = modifier

                .fillMaxWidth(),
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit
        ) {
            when (painter.state) {
                is AsyncImagePainter.State.Error -> {
                    Log.e("RemoteImage", "AsyncImagePainter.State.Error ")
                }

                is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                else -> Image(
                    painter = painter,
                    contentDescription = contentDescription,
//                colorFilter = ColorFilter.tint(Color.Red)
                )
            }
        }
    }


}

@Preview
@Composable
fun PreviewRemoteImagePng() {
    RemoteImage(
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/12.png",
        placeholderResource = R.drawable.pokeball_icon,
        errorResource = R.drawable.pokeball_icon,
        contentDescription = ""
    )
}

@Preview
@Composable
fun PreviewRemoteImageSvg() {
    RemoteImage(
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg",
        placeholderResource = R.drawable.pokeball_icon,
        errorResource = R.drawable.pokeball_icon,
        contentDescription = ""
    )
}