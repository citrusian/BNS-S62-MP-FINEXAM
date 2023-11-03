package com.example.bns_s62_mp_finexam.Utility

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.bns_s62_mp_finexam.R
import java.net.URLEncoder

@Composable
fun dataListColumn(
    navController: NavHostController,
    detailsProvince: List<Any>,
    imageUrls: List<Any>,
    destinationMap: Map<String, String>? = null,
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(detailsProvince.size) { index ->
            val defaultDrawable = R.drawable.baseline_error_outline_24
            val item = imageUrls.getOrNull(index) ?: R.drawable.baseline_error_outline_24
            val imageType = determineImageType(item)

            val painter: Painter = when (imageType) {
                ImageType.DrawableResource -> rememberAsyncImagePainter(item)
                ImageType.URL -> rememberAsyncImagePainter(item)
                ImageType.Unknown -> painterResource(defaultDrawable)
            }

            val details = detailsProvince[index]
            val destination = destinationMap?.get(details) ?: "homescreen"

            ImageCardOneLine(
                painter = painter,
                contentDescription = "Logo Province",
                detailsProvince = details,
                modifier = Modifier
                    .clickable {
                        destination?.let { route ->
                        navController.navigate(route)
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            SimpleText("Data Habis")
        }
    }
}


@Composable
fun dataListColumnDTF(
    navController: NavHostController,
    detailsDaerah: List<Any>,
    imageUrls: List<Any>,
    destinationMap: String? = "homescreen",
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(detailsDaerah.size) { index ->
            val defaultDrawable = R.drawable.baseline_error_outline_24
            val item = imageUrls.getOrNull(index) ?: defaultDrawable
            val imageType = determineImageType(item)

            val painter: Painter = when (imageType) {
                ImageType.DrawableResource -> rememberAsyncImagePainter(item)
                ImageType.URL -> rememberAsyncImagePainter(item)
                ImageType.Unknown -> painterResource(defaultDrawable)
            }

            val details = detailsDaerah[index]
            val destination = destinationMap

            // Don't test again, it was determined the "/" slash causing error when not encoded
            val encodedItem = URLEncoder.encode(item.toString(), "UTF-8")
//            Log.d("DEBUG", "encoded Item: $encodedItem")

            ImageCardOneLine(
                painter = painter,
                contentDescription = "Logo Province",
                detailsProvince = details,
                modifier = Modifier
                    .clickable {
                        destination?.let { route ->
                            navController.navigate("listAlamat/$details/$encodedItem")
                            Log.d("DEBUG", "staticImages Pass 2: $encodedItem")
                        }
                    }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            SimpleText("Data Habis")
        }
    }
}


// Test Using ANY, combine both Data List navigation
@Composable
fun dataListColumnTEST(
    navController: NavHostController,
    detailsDaerah: List<Any>,
    imageUrls: List<Any>,
    destinationMap: Any? = null
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(detailsDaerah.size) { index ->
            val defaultDrawable = R.drawable.baseline_error_outline_24
            val item = imageUrls.getOrNull(index) ?: defaultDrawable
            val encodedItem = URLEncoder.encode(item.toString(), "UTF-8")
            val imageType = determineImageType(item)

            val painter: Painter = when (imageType) {
                ImageType.DrawableResource -> rememberAsyncImagePainter(item)
                ImageType.URL -> rememberAsyncImagePainter(item)
                ImageType.Unknown -> painterResource(defaultDrawable)
            }

            val details = detailsDaerah[index]

            val (destinationFlag, destination) = when (destinationMap) {
                is Map<*, *> -> true to (destinationMap as? Map<*, *>)?.get(details)
                is String -> false to destinationMap
                else -> false to "homescreen"
            }

            if (destinationFlag) {
                ImageCardOneLine(
                    painter = painter,
                    contentDescription = "Logo Province",
                    detailsProvince = details,
                    modifier = Modifier
                        .clickable {
                            (destination as? String)?.let { route ->
                                navController.navigate(route)
                            }
                        }
                )
            } else {
                ImageCardOneLine(
                    painter = painter,
                    contentDescription = "Logo Province",
                    detailsProvince = details,
                    modifier = Modifier
                        .clickable {
                            destination?.let { route ->
                                navController.navigate("listAlamat/$details/$encodedItem")
                                Log.d("DEBUG", "staticImages Pass 2: $encodedItem")
                            }
                        }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            SimpleText("Data Habis")
        }
    }
}