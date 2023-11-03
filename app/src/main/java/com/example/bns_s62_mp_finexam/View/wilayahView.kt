package com.example.bns_s62_mp_finexam.View

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar
import com.example.bns_s62_mp_finexam.R
import com.example.bns_s62_mp_finexam.Utility.DataListRenderer


@Preview(showBackground = true)
@Composable
fun WilayahViewPreview() {
    val navController = rememberNavController()
    WilayahView(navController)
}

@Composable
fun WilayahView(navController: NavHostController) {
    Column {
        HeaderBar("Alamat", false, false, navController)

        // manual is preferred,
        // because it's consist of 6 items and easy to test
        val listProvince = listOf(
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

        val destinationMap = mapOf(
            "Wilayah Sumatera" to "provinsiSumatera",
            "Wilayah Jawa" to "provinsiJawa",
            "Wilayah Sulawesi" to "provinsiSulawesi",
            "Wilayah Kalimantan" to "provinsiKalimantan",
            "Wilayah Bali, Nusa Tenggara, Maluku" to "provinsiBali",
            "Wilayah Papua" to "provinsiPapua",
        )

        DataListRenderer(
            navController,
            listProvince,
            imageUrls,
            destinationMap
        )
    }
}