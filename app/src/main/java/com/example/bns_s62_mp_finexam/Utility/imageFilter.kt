package com.example.bns_s62_mp_finexam.Utility

fun determineImageType(item: Any): ImageType {
    return when (item) {
        is Int -> ImageType.DrawableResource
        is String -> ImageType.URL
        else -> ImageType.Unknown
    }
}

// An enum to represent image types
enum class ImageType {
    DrawableResource,
    URL,
    Unknown
}