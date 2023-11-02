package com.example.bns_s62_mp_finexam.Utility

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
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
    detailsProvince: List<Any>,
    imageUrls: List<Any>,
    destinationMap: Map<String, String>? = null
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(detailsProvince.size) { index ->
            val defaultDrawable = R.drawable.baseline_error_outline_24
            val item = imageUrls.getOrNull(index) ?: defaultDrawable
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
fun dataListColumnBig(
    navController: NavHostController,
    detailsProvince: List<Any>,
    imageUrls: List<Any>,
    destinationMap: Map<String, String>? = null
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(detailsProvince.size) { index ->
            val defaultDrawable = R.drawable.baseline_error_outline_24
            val item = imageUrls.getOrNull(index) ?: defaultDrawable
            val imageType = determineImageType(item)

            val painter: Painter = when (imageType) {
                ImageType.DrawableResource -> rememberAsyncImagePainter(item)
                ImageType.URL -> rememberAsyncImagePainter(item)
                ImageType.Unknown -> painterResource(defaultDrawable)
            }

            val details = detailsProvince[index]
            val destination = destinationMap?.get(details) ?: "homescreen"
//            val routing = destination +"/"+ details

            val onClick: () -> Unit = {
                destination?.let { route ->
                    navController.navigate(route)
                }
            }


            // add fail over if somehow the urls gave an error
            ImageCardOneLine(
                painter = painter,
                contentDescription = "Logo Province",
                detailsProvince = details,
                modifier = Modifier
                    .clickable(onClick = onClick)
//                    .clickable {
//                        destination?.let { route ->
//                            navController.navigate(route)
//                        }
//                    }
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
        // Add a Text composable after the last item
        item {
            SimpleText("Data Habis")
        }
    }
}