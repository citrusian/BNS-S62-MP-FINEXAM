package com.example.bns_s62_mp_finexam.Adrress

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar
import com.example.bns_s62_mp_finexam.R
import com.example.bns_s62_mp_finexam.Utility.ImageCardOneLine
import com.example.bns_s62_mp_finexam.Utility.ImageType
import com.example.bns_s62_mp_finexam.Utility.SimpleText
import com.example.bns_s62_mp_finexam.Utility.dataListColumn
import com.example.bns_s62_mp_finexam.Utility.determineImageType


@Preview(showBackground = true)
@Composable
fun AlamatWilayahPreview() {
    val navController = rememberNavController()
    AlamatWilayahView(navController)
}


@Composable
fun AlamatWilayahView(navController: NavHostController) {
    Column {
        HeaderBar("Alamat", true, false, navController)
        AlamatWilayahListView(navController)
    }
}

// using LazyColumn, eg: RecycleView
@Composable
fun AlamatWilayahListView(navController: NavHostController) {

    val destinationMap = mapOf(
        "Wilayah Sumatera" to "provinsiSumatera",
        "Wilayah Jawa" to "provinsiJawa",
        "Wilayah Sulawesi" to "provinsiSulawesi",
        "Wilayah Kalimantan" to "provinsiKalimantan",
        "Wilayah Bali, Nusa Tenggara, Maluku" to "provinsiBali",
        "Wilayah Papua" to "provinsiPapua",
    )

    val detailsProvince = listOf(
        "Wilayah Sumatera",
        "Wilayah Jawa",
        "Wilayah Sulawesi",
        "Wilayah Kalimantan",
        "Wilayah Bali, Nusa Tenggara, Maluku",
        "Wilayah Papua",
    )

    val imageUrls = listOf(
        R.drawable.sumatera,
        R.drawable.jawa,
        R.drawable.sulawesi,
        R.drawable.kalimantan,
        R.drawable.bali,
        R.drawable.papua,
    )

    dataListColumn(
        navController,
        detailsProvince,
        imageUrls,
        destinationMap
    )

//    LazyColumn (
//        modifier = Modifier.fillMaxSize(),
//        contentPadding = PaddingValues(10.dp)
//    ) {
//        items(detailsProvince.size) { index ->
//            val defaultDrawable = R.drawable.baseline_error_outline_24
//            // check if imageUrls is null and which type (url or vector)
//            // default to err outline, fail over for array size
//            val item = imageUrls.getOrNull(index) ?: defaultDrawable
//            val imageType = determineImageType(item)
//
//            val painter: Painter = when (imageType) {
//                ImageType.DrawableResource -> painterResource(id = item as Int)
//                ImageType.URL -> rememberAsyncImagePainter(item)
//                ImageType.Unknown -> painterResource(defaultDrawable)
//            }
//
//
//            val details = detailsProvince[index]
//            val destination = destinationMap[details]
//            // add fail over if somehow the urls gave an error
//            ImageCardOneLine(
//                painter = painter,
//                contentDescription = "Logo Province",
//                detailsProvince = details,
//                modifier = Modifier.clickable {
//                    // Test using clickable
//                    destination?.let { route ->
//                        navController.navigate(route)
//                    }
//                }
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//        }
//        // Add a Text composable after the last item
//        item {
//            SimpleText("Akhir Daftar Wilayah")
//        }
//    }
}