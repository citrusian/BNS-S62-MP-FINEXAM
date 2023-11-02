package com.example.bns_s62_mp_finexam.Adrress.Provinsi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar
import com.example.bns_s62_mp_finexam.R
import com.example.bns_s62_mp_finexam.Utility.ImageCardOneLine
import com.example.bns_s62_mp_finexam.Utility.ImageType
import com.example.bns_s62_mp_finexam.Utility.SimpleText
import com.example.bns_s62_mp_finexam.Utility.determineImageType


@Preview(showBackground = true)
@Composable
fun AlamatDaerahPreview() {
    val navController = rememberNavController()
    AlamatDaerahView(navController)
}


@Composable
fun AlamatDaerahView(navController: NavHostController) {
    Column {
        HeaderBar("Alamat", true, false, navController)
        AlamatDaerahListView(navController)
    }
}


// https://www.youtube.com/watch?v=KPVoQjwmWX4
// Tutorial

// using LazyColumn, eg: RecycleView
@Composable
fun AlamatDaerahListView(navController: NavHostController) {

    val imageUrls = listOf(
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2F2d4b8fde-5ec2-4c72-b804-29d3cc14e3d7.799a99c1196c2fd4.gif&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75"
    )

    val detailsDaerah = listOf(
        "Aceh",
        "Sumatera Utara",
        "Sumatera Barat",
        "Riau",
        "Jambi",
        "Sumatera Selatan",
        "Bengkulu",
        "Lampung",
        "Bangka Belitung",
        "Kepulauan Riau",
    )

    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(detailsDaerah.size) { index ->
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


            val details = detailsDaerah[index]
//            val destination = destinationMap[details]
            // add fail over if somehow the urls gave an error
            ImageCardOneLine(
                painter = painter,
                contentDescription = "Logo Province",
                detailsProvince = details,
//                modifier = Modifier.clickable {
//                    // Test using clickable
//                    destination?.let { route ->
//                        navController.navigate(route)
//                    }
//                }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Add a Text composable after the last item
        item {
            SimpleText("Akhir Daftar Daerah")
        }
    }
}

