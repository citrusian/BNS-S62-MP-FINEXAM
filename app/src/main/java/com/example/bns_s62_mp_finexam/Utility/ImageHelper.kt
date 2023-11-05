package com.example.bns_s62_mp_finexam.Utility

import android.app.Application
import coil.ComponentRegistry
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger

// coil need SVG extension to load svg from url
// https://coil-kt.github.io/coil/svgs/
// https://stackoverflow.com/questions/69388639/how-to-load-remote-svg-image-using-coil-in-jetpack-compose
// https://github.com/coil-kt/coil/issues/1207 -- Override Method
//

// Cache test
// https://www.youtube.com/watch?v=qQVCtkg-O7w&t=721s
class CoilOverride : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components(fun ComponentRegistry.Builder.() {
                add(SvgDecoder.Factory())
            })
            // Ram Cache
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
//                    // Real Device Value, 10% of remaining ram
//                    .maxSizePercent(0.1)
                    // Emulator Test
                    .maxSizePercent(1.0)
//                    .strongReferencesEnabled(true)
                    .build()
            }
            // Disk Cache
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
//                    // Real Device Value, 5% of remaining rom
//                    .maxSizePercent(0.05)
                    // Emulator Test
                    .maxSizePercent(1.0)
                    .directory(cacheDir)
                    .build()
            }
            .logger(DebugLogger())

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