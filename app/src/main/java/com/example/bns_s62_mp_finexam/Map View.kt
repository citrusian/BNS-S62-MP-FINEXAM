package com.example.bns_s62_mp_finexam

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar


@Preview(showBackground = true)
@Composable
fun MapViewPrev() {
    val navController = rememberNavController()
    MapView("Testo",navController)
}


@Composable
fun MapView(
    title: String,
    navController: NavHostController
) {
    Column {
        HeaderBar("Map", false, true, navController)
        Text(
            text = "Selamat datang pada aplikasi\n" +
                    "daftar alamat kantor gubernur\n" +
                    "seluruh Indonesia" +
                    "\n\n" +
                    "Silahkan Tekan Tombol Alamat di bawah\n" +
                    "untuk mulai mencari alamat yang kantor\n" +
                    "Gubernur yang terdaftar"
            ,
            style = TextStyle(
                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
            )
        )
    }
}
