package com.example.bns_s62_mp_finexam.Utility

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

@Composable
fun dataListColumn(
    navController: NavHostController,
    // use any to accept img url or drawable vec
    detailsProvince: List<Any>,
    imageUrls: List<Any>,
//    destinationMap: Map<String, Any>,
    destinationMap: Map<String, String>? = null
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(detailsProvince.size) { index ->
            val defaultDrawable = R.drawable.baseline_error_outline_24
            // check if imageUrls is null and which type (url or vector)
            // default to err outline, fail over for array size
            val item = imageUrls.getOrNull(index) ?: defaultDrawable
            val imageType = determineImageType(item)

            val painter: Painter = when (imageType) {
                ImageType.DrawableResource -> painterResource(id = item as Int)
                ImageType.URL -> rememberAsyncImagePainter(item)
                ImageType.Unknown -> painterResource(defaultDrawable)
            }

            val details = detailsProvince[index]
//            val destination = destinationMap?.getOrDefault(details, "homescreen")
            val destination = destinationMap?.get(details) ?: "homescreen"
//            val destination = destinationMap?.get(details)


            // add fail over if somehow the urls gave an error
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
        // Add a Text composable after the last item
        item {
            SimpleText("Data Habis")
        }
    }
}