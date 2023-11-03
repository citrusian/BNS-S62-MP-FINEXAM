package com.example.bns_s62_mp_finexam

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
    Column {
        HeaderBar("Home", false, false, navController)
        Spacer(Modifier.height(15.dp))
        SimpleText("Selamat datang di aplikasi untuk mencari daftar alamat kantor gubernur di seluruh Indonesia",20,TextAlign.Justify)
        Spacer(Modifier.height(20.dp))
        SimpleText("Silakan tekan tombol *ALAMAT* di bawah ini untuk memulai pencarian alamat kantor gubernur yang terdaftar.",20,TextAlign.Justify)
        Spacer(Modifier.height(15.dp))
    }
}
