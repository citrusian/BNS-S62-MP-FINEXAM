package com.example.bns_s62_mp_finexam.Adrress

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar
import com.example.bns_s62_mp_finexam.R
import com.example.bns_s62_mp_finexam.Utility.dataListColumn
import com.example.bns_s62_mp_finexam.Utility.dataListColumnDTF
import com.example.bns_s62_mp_finexam.Utility.dataListColumnTEST


@Preview(showBackground = true)
@Composable
fun AlamatWilayahPreview() {
    val navController = rememberNavController()
    wilayahIndonesiaView(navController)
}

@Composable
fun wilayahIndonesiaView(navController: NavHostController) {
    Column {
        HeaderBar("Alamat", true, false, navController)

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

        dataListColumnTEST(
            navController,
            listProvince,
            imageUrls,
            destinationMap
        )
    }
}