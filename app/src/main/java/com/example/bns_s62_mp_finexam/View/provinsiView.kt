package com.example.bns_s62_mp_finexam.View

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar
import com.example.bns_s62_mp_finexam.Utility.AppContextProvider
import com.example.bns_s62_mp_finexam.Utility.DataListRenderer
import com.example.bns_s62_mp_finexam.Utility.JSONwilayahProcessor


@Preview(showBackground = true)
@Composable
fun provinsiViewPreview() {
    val navController = rememberNavController()
    ProvinsiView(navController, "Wilayah Sumatera")
}

@Composable
fun ProvinsiView(
    navController: NavHostController,
    wilayah : String?,
) {
    Column {
        HeaderBar("Alamat", false, true, navController)
        val wilayahStr = wilayah.toString()
        val appContext = AppContextProvider.getInstance().getAppContext()
        val jsonProcessor = JSONwilayahProcessor(appContext, wilayahStr)
        val detailsDaerah = jsonProcessor.getWilayahProvince()
        val imageUrls = jsonProcessor.getWilayahLogo()
        val destinationMap = "listAlamat"

        DataListRenderer(
            navController,
            detailsDaerah,
            imageUrls,
            destinationMap
        )
    }
}