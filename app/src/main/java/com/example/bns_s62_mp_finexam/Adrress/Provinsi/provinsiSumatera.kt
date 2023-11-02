package com.example.bns_s62_mp_finexam.Adrress.Provinsi

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar
import com.example.bns_s62_mp_finexam.Utility.dataListColumn


@Preview(showBackground = true)
@Composable
fun provinsiSumateraPreview() {
    val navController = rememberNavController()
    provinsiSumateraView(navController)
}


@Composable
fun provinsiSumateraView(navController: NavHostController) {
    Column {
        HeaderBar("Alamat", true, false, navController)
        provinsiSumateraListView(navController)
    }
}

@Composable
fun provinsiSumateraListView(navController: NavHostController) {

    val imageUrls = listOf(
        "https://upload.wikimedia.org/wikipedia/commons/4/41/Coat_of_arms_of_Aceh.svg",
        "https://upload.wikimedia.org/wikipedia/commons/c/c8/Coat_of_arms_of_North_Sumatra.svg",
        "https://upload.wikimedia.org/wikipedia/commons/6/62/Coat_of_arms_of_West_Sumatra.svg",
        "https://upload.wikimedia.org/wikipedia/commons/4/45/Coat_of_arms_of_South_Sumatra.svg",
        "https://upload.wikimedia.org/wikipedia/commons/0/0b/Coat_of_arms_of_Riau.svg",
        "https://upload.wikimedia.org/wikipedia/commons/5/54/Coat_of_arms_of_Riau_Islands.svg",
        "https://upload.wikimedia.org/wikipedia/commons/f/f2/Coat_of_arms_of_Jambi.svg",
        "https://upload.wikimedia.org/wikipedia/commons/5/54/Coat_of_arms_of_Bengkulu.svg",
        "https://upload.wikimedia.org/wikipedia/commons/b/b9/Lampung_coa.png",
        "https://upload.wikimedia.org/wikipedia/commons/0/08/Coat_of_arms_of_Bangka_Belitung_Islands.svg",
    )

    val detailsDaerah = listOf(
        "Aceh",
        "Sumatera Utara",
        "Sumatera Barat",
        "Sumatera Selatan",
        "Riau",
        "Kepulauan Riau",
        "Jambi",
        "Bengkulu",
        "Lampung",
        "Bangka Belitung",
    )

    val destinationMap = mapOf(
        "Aceh" to "listAlamat",
        "Sumatera Utara" to "listAlamat",
        "Sumatera Barat" to "listAlamat",
        "Sumatera Selatan" to "listAlamat",
        "Riau" to "listAlamat",
        "Kepulauan Riau" to "listAlamat",
        "Jambi" to "listAlamat",
        "Bengkulu" to "listAlamat",
        "Lampung" to "listAlamat",
        "Bangka Belitung" to "listAlamat",
    )

    dataListColumn(
        navController,
        detailsDaerah,
        imageUrls,
        destinationMap
    )
}

