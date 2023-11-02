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
    destinationMap: Map<String, String>? = null
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
fun dataListProvinsiColumn(
    navController: NavHostController,
    detailsProvince: List<Any>,
    imageUrls: List<Any>,
//    destinationMap: Map<String, String>? = null,
    destinationMap: String? = null,
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
//            val destination = destinationMap?.get(details) ?: "homescreen"
            val destination = destinationMap

//            val staticImage = when (details){
//                "Aceh" -> "https://upload.wikimedia.org/wikipedia/commons/4/41/Coat_of_arms_of_Aceh.svg"
//                "Sumatera Utara" -> "https://upload.wikimedia.org/wikipedia/commons/c/c8/Coat_of_arms_of_North_Sumatra.svg"
//                "Sumatera Barat" -> "https://upload.wikimedia.org/wikipedia/commons/6/62/Coat_of_arms_of_West_Sumatra.svg"
//                "Sumatera Selatan" -> "https://upload.wikimedia.org/wikipedia/commons/4/45/Coat_of_arms_of_South_Sumatra.svg"
//                "Riau" -> "https://upload.wikimedia.org/wikipedia/commons/0/0b/Coat_of_arms_of_Riau.svg"
//                "Kepulauan Riau" -> "https://upload.wikimedia.org/wikipedia/commons/5/54/Coat_of_arms_of_Riau_Islands.svg"
//                "Jambi" -> "https://upload.wikimedia.org/wikipedia/commons/f/f2/Coat_of_arms_of_Jambi.svg"
//                "Bengkulu" -> "https://upload.wikimedia.org/wikipedia/commons/5/54/Coat_of_arms_of_Bengkulu.svg"
//                "Lampung" -> "https://upload.wikimedia.org/wikipedia/commons/b/b9/Lampung_coa.png"
//                "Bangka Belitung" -> "https://upload.wikimedia.org/wikipedia/commons/0/08/Coat_of_arms_of_Bangka_Belitung_Islands.svg"
//                else -> "R.drawable.baseline_error_outline_24"
//            }
//            Log.d("DEBUG", "staticImages: $staticImage")


            // Try to encode it first before pass the value
//            val encodedItem = URLEncoder.encode(staticImage, "UTF-8")
            val encodedItem = URLEncoder.encode(item.toString(), "UTF-8")
//            Log.d("DEBUG", "Details Item: $item")
            Log.d("DEBUG", "encoded Item: $encodedItem")
//            Log.d("DEBUG", "staticImages Pass 1: $staticImage")










            ImageCardOneLine(
                painter = painter,
                contentDescription = "Logo Province",
                detailsProvince = details,
                modifier = Modifier
                    .clickable {
                        destination?.let { route ->
//                            navController.navigate("listAlamat/$details")
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