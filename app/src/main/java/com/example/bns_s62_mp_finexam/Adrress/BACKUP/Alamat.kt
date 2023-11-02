package com.example.bns_s62_mp_finexam.Adrress.BACKUP

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar
import com.example.bns_s62_mp_finexam.Utility.ImageCardBig
import com.example.bns_s62_mp_finexam.Utility.SimpleText


@Preview(showBackground = true)
@Composable
fun AlamatViewPreview() {
    val navController = rememberNavController()
    AlamatView(navController)
}


@Composable
fun AlamatView(navController: NavHostController) {
    Column {
        HeaderBar("Alamat", true, false, navController)
        AdrressListView(navController)
    }
}


// https://www.youtube.com/watch?v=KPVoQjwmWX4
// Tutorial

// using LazyColumn, eg: RecycleView
@Composable
fun AdrressListView(navController: NavHostController) {

    val imageUrls = listOf(
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2F2d4b8fde-5ec2-4c72-b804-29d3cc14e3d7.799a99c1196c2fd4.gif&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75"
    )

    val locationList = listOf(
        "Location 1 ",
        "Location 2 ",
        "Location 3 ",
        "Location 4 ",
    )

    val detailsProvince = listOf(
        "Aceh",
        "D.I Yogyakarta",
        "Google",
        "Google",
    )

    val detailsAddress = listOf(
        "JL. T. Nyak Arief No.219, Banda Aceh (23114)",
        "JL. Malioboro No.16, Suryatmajan, Komplek Kepatihan  Danurejan, Kota Yogyakarta (55213)",
        "Google",
        "Google",
    )

    val detailsWebsite = listOf(
        "www.acehprov.go.id",
        "www.jogjaprov.go.id",
        "Google",
        "Google",
    )

    val detailsPhone = listOf(
        "T: (0651)-7551377  F: (0651)-7552307,  7555516, 7555518, 7553048,",
        "(0274)-562811,  512655",
        "Google",
        "Google",
    )

    val detailsMail = listOf(
        "humas@acehprov.go.id",
        "null",
        "Google",
        "Google",
    )

    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(imageUrls.size) { index ->
            ImageCardBig(
                painter = rememberAsyncImagePainter(imageUrls[index]),
//                contentDescription = "${imageDesc[index]} ${index + 1}",
                contentDescription = "Logo Province",
                // Test using index to call desc, and call index number +1
                // title = "Google Doodle ${index + 1}",
                detailsProvince = detailsProvince[index],
                detailsAddress = detailsAddress[index],
                detailsWebsite = detailsWebsite[index],
                detailsPhone = detailsPhone[index],
                detailsMail = detailsMail[index],
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Add a Text composable after the last item
        item {
            SimpleText("Akhir Daftar Alamat")
        }
    }
}