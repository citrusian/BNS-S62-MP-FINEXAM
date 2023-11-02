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
import com.example.bns_s62_mp_finexam.Utility.ImageType
import com.example.bns_s62_mp_finexam.Utility.TextCardBig
import com.example.bns_s62_mp_finexam.Utility.SimpleText
import com.example.bns_s62_mp_finexam.Utility.determineImageType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.app.Application
import android.content.Context
import android.util.Log
import com.example.bns_s62_mp_finexam.Utility.AppContextProvider
import com.example.bns_s62_mp_finexam.Utility.CoilEX
import com.example.bns_s62_mp_finexam.Utility.DataItem


@Preview(showBackground = true)
@Composable
fun listAlamatPreview() {
    val navController = rememberNavController()
    listAlamatView(navController,"Aceh")
}


@Composable
fun listAlamatView(
    navController: NavHostController,
    daerah: String?,
) {
    Column {
        HeaderBar("Alamat", true, false, navController)
        listAlamatListView(navController,daerah)
    }
}

@Composable
fun listAlamatListView(
    navController: NavHostController,
    daerah: String?,
) {
    Log.d("DEBUG", "Details Province: $daerah")

    val appContext = AppContextProvider.getAppContext()
    val jsonString = appContext.assets.open("data.json").bufferedReader().use { it.readText() }
    val gson = Gson()
    val dataItems: List<DataItem> = gson.fromJson(jsonString, object : TypeToken<List<DataItem>>() {}.type)

    val selectedDaerah = "Aceh" // Replace with the desired "DAERAH" value
    val filteredItems = dataItems.filter { it.DAERAH == selectedDaerah }

    // get data from json using item filter
    // PROVINSI
    val detailsProvince = filteredItems.flatMap { it.DATA.mapNotNull { subDataItem -> subDataItem.PROVINSI } }
    // ALAMAT
    val detailsAddress = filteredItems.flatMap { it.DATA.mapNotNull { subDataItem -> subDataItem.ALAMAT } }
    // WEBSITE
    val detailsWebsite = filteredItems.flatMap { it.DATA.mapNotNull { subDataItem -> subDataItem.WEBSITE } }
    // TEL/FAX
    val detailsPhone = filteredItems.flatMap { it.DATA.mapNotNull { subDataItem -> subDataItem.TEL_FAX } }
    // E-MAIL
    val detailsMail = filteredItems.flatMap { it.DATA.mapNotNull { subDataItem -> subDataItem.E_MAIL } }



    Log.d("DEBUG", "Details Province: $detailsProvince")

//    val detailsProvince = listOf(
//        "Aceh",
//        "Kab. Aceh Selatan",
//        "etc",
//        "etc",
//    )

//    val detailsAddress = listOf(
//        "JL. T. Nyak Arief No.219, Banda Aceh (23114)",
//        "JL. Malioboro No.16, Suryatmajan, Komplek Kepatihan  Danurejan, Kota Yogyakarta (55213)",
//        "etc",
//        "etc",
//    )
//
//    val detailsWebsite = listOf(
//        "www.acehprov.go.id",
//        "www.jogjaprov.go.id",
//        "etc",
//        "etc",
//    )
//
//    val detailsPhone = listOf(
//        "T: (0651)-7551377  F: (0651)-7552307,  7555516, 7555518, 7553048,",
//        "(0274)-562811,  512655",
//        "etc",
//        "etc",
//    )
//
//    val detailsMail = listOf(
//        "humas@acehprov.go.id",
//        "etc",
//        "etc",
//        "etc",
//    )

    val imageUrls = listOf(
        "https://upload.wikimedia.org/wikipedia/commons/0/08/Coat_of_arms_of_Bangka_Belitung_Islands.svg",
        "https://upload.wikimedia.org/wikipedia/commons/0/08/Coat_of_arms_of_Bangka_Belitung_Islands.svg",
        "https://upload.wikimedia.org/wikipedia/commons/0/08/Coat_of_arms_of_Bangka_Belitung_Islands.svg",
        )


    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(detailsProvince.size) { index ->
            val defaultDrawable = R.drawable.baseline_error_outline_24
            val item = imageUrls.getOrNull(index) ?: defaultDrawable
            val imageType = determineImageType(item)

            val painter: Painter = when (imageType) {
                ImageType.DrawableResource -> rememberAsyncImagePainter(item)
                ImageType.URL -> rememberAsyncImagePainter(item)
                ImageType.Unknown -> painterResource(defaultDrawable)
            }
            TextCardBig(
                painter = painter,
                contentDescription = "Logo Province",
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

