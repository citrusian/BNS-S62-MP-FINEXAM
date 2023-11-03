package com.example.bns_s62_mp_finexam.Utility

import android.app.Application
import coil.ComponentRegistry
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder

// coil need SVG extension to load svg from url
// https://coil-kt.github.io/coil/svgs/
// https://stackoverflow.com/questions/69388639/how-to-load-remote-svg-image-using-coil-in-jetpack-compose
// https://github.com/coil-kt/coil/issues/1207 -- Override Method
//
class CoilEX : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components(fun ComponentRegistry.Builder.() {
                add(SvgDecoder.Factory())
            })
            .build()
    }
}

fun determineImageType(item: Any): ImageType {
    return when (item) {
        is Int -> ImageType.DrawableResource
        is String -> ImageType.URL
        else -> ImageType.Unknown
    }
}

// images type enum
enum class ImageType {
    DrawableResource,
    URL,
    Unknown
}