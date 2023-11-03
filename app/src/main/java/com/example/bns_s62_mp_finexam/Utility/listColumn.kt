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
fun DataListRenderer(
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

            // Check if Destination has 1 or 2 passable
            if (destinationFlag) {
                ImageCardMedium(
                    painter = painter,
                    contentDescription = "Logo Province",
                    detailsProvince = details,
                    modifier = Modifier
                        .clickable {
                            destination?.let { route ->
                                navController.navigate("listProvinsi/$details")
                            }
                        }
                )
            } else {
                ImageCardMedium(
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
            Spacer(Modifier.height(10.dp))
        }
        item {
            SimpleText20SPFILL("Data Habis")
        }
    }
}