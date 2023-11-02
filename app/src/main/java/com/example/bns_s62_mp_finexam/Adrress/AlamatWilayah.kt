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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar
import com.example.bns_s62_mp_finexam.Utility.ImageCardOneLine
import com.example.bns_s62_mp_finexam.Utility.ListViewSimpleFailover
import com.example.bns_s62_mp_finexam.Utility.SimpleText


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

    val imageUrls = listOf(
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2F2d4b8fde-5ec2-4c72-b804-29d3cc14e3d7.799a99c1196c2fd4.gif&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75"
    )

    val detailsProvince = listOf(
        "Wilayah Sumatera",
        "Wilayah Jawa",
        "Wilayah Sulawesi",
        "Wilayah Kalimantan",
        "Wilayah Bali, Nusa Tenggara, Maluku",
        "Wilayah Papua",
    )

    val destinationMap = mapOf(
        "Wilayah Sumatera" to "provinsiSumatera",
        "Wilayah Jawa" to "provinsiJawa",
        "Wilayah Sulawesi" to "provinsiSulawesi",
        "Wilayah Kalimantan" to "provinsiKalimantan",
        "Wilayah Bali, Nusa Tenggara, Maluku" to "provinsiBali",
        "Wilayah Papua" to "provinsiPapua",
    )

    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(detailsProvince.size) { index ->
            val imageUrl = imageUrls.getOrNull(index)
            val details = detailsProvince[index]
            val destination = destinationMap[details]
            // add fail over if somehow the urls gave an error
            if (imageUrl != null) {
                ImageCardOneLine(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = "Logo Province",
                    detailsProvince = details,
                    modifier = Modifier.clickable {
                        // Test using clickable
                        destination?.let { route ->
                            navController.navigate(route)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            } else {
                // Render only the detailsProvince when imageUrl is null
                ListViewSimpleFailover(detailsProvince = details)
            }
        }
        // Add a Text composable after the last item
        item {
            SimpleText("Akhir Daftar Wilayah")
        }
    }
}