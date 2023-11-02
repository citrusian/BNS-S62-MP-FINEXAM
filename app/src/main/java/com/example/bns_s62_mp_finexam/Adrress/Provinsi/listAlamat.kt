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
import java.net.URLDecoder


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
//    item: String?,
) {
    Column {
        HeaderBar("Alamat", true, false, navController)
        listAlamatListView(navController,daerah, )
    }
}

@Composable
fun listAlamatListView(
    navController: NavHostController,
//    daerah: String?="ACEH",
    daerah: String?,
//    item: String?,
) {
    Log.d("DEBUG", "Details Daerah: $daerah")
//    Log.d("DEBUG", "Details Item: $item")
//    val item = URLDecoder.decode(item, "UTF-8")
//    Log.d("DEBUG", "Decoded Item: $item")

    val appContext = AppContextProvider.getAppContext()
    val jsonString = appContext.assets.open("data.json").bufferedReader().use { it.readText() }
    val gson = Gson()
    val dataItems: List<DataItem> = gson.fromJson(jsonString, object : TypeToken<List<DataItem>>() {}.type)

//    val selectedDaerah = "Aceh" // DEBUG ACEH
    val selectedDaerah = daerah
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

    Log.d("DEBUG", "Details Daerah: $daerah")
//    Log.d("DEBUG", "Details Item: $item")


    val staticImage = when (daerah){
        "Aceh" -> "https://upload.wikimedia.org/wikipedia/commons/4/41/Coat_of_arms_of_Aceh.svg"
        "Sumatera Utara" -> "https://upload.wikimedia.org/wikipedia/commons/c/c8/Coat_of_arms_of_North_Sumatra.svg"
        "Sumatera Barat" -> "https://upload.wikimedia.org/wikipedia/commons/6/62/Coat_of_arms_of_West_Sumatra.svg"
        "Sumatera Selatan" -> "https://upload.wikimedia.org/wikipedia/commons/4/45/Coat_of_arms_of_South_Sumatra.svg"
        "Riau" -> "https://upload.wikimedia.org/wikipedia/commons/0/0b/Coat_of_arms_of_Riau.svg"
        "Kepulauan Riau" -> "https://upload.wikimedia.org/wikipedia/commons/5/54/Coat_of_arms_of_Riau_Islands.svg"
        "Jambi" -> "https://upload.wikimedia.org/wikipedia/commons/f/f2/Coat_of_arms_of_Jambi.svg"
        "Bengkulu" -> "https://upload.wikimedia.org/wikipedia/commons/5/54/Coat_of_arms_of_Bengkulu.svg"
        "Lampung" -> "https://upload.wikimedia.org/wikipedia/commons/b/b9/Lampung_coa.png"
        "Bangka Belitung" -> "https://upload.wikimedia.org/wikipedia/commons/0/08/Coat_of_arms_of_Bangka_Belitung_Islands.svg"
        else -> "R.drawable.baseline_error_outline_24"
    }
    Log.d("DEBUG", "staticImages: $staticImage")


    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(detailsProvince.size) { index ->
            val defaultDrawable = R.drawable.baseline_error_outline_24
            val item = staticImage.getOrNull(index) ?: defaultDrawable
            val imageType = determineImageType(item)

            // Disable, already have multiple check above
//            val painter: Painter = when (imageType) {
//                ImageType.DrawableResource -> rememberAsyncImagePainter(staticImage)
//                ImageType.URL -> rememberAsyncImagePainter(staticImage)
//                ImageType.Unknown -> painterResource(defaultDrawable)
//            }

            val painter: Painter = rememberAsyncImagePainter(staticImage)

            Log.d("DEBUG", "painter: $painter")
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

