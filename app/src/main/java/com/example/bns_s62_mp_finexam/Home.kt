package com.example.bns_s62_mp_finexam

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar
import com.example.bns_s62_mp_finexam.Utility.SimpleText


@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    val navController = rememberNavController()
    HomeView(navController)
}

@Composable
fun HomeView(navController: NavHostController) {
    // TODO: HomeView
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderBar("Home", false, false, navController)
//        val painter = rememberAsyncImagePainter("https://upload.wikimedia.org/wikipedia/commons/9/90/National_emblem_of_Indonesia_Garuda_Pancasila.svg")
        val painter = rememberAsyncImagePainter(R.drawable.garuda_vector)

        Spacer(Modifier.height(15.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentDescription = "Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(height = 200.dp, width = 200.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
        }
        Spacer(Modifier.height(20.dp))
        SimpleText("Selamat datang di aplikasi untuk mencari daftar alamat kantor gubernur di seluruh Indonesia",20,TextAlign.Justify)
        Spacer(Modifier.height(20.dp))
        SimpleText("Silakan tekan tombol *ALAMAT* di bawah ini untuk memulai pencarian alamat kantor gubernur yang terdaftar.",20,TextAlign.Justify)
        Spacer(Modifier.height(15.dp))
    }
}
