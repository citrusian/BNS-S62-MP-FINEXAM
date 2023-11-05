package com.example.bns_s62_mp_finexam.View

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar
import com.example.bns_s62_mp_finexam.Utility.AppContextProvider
import com.example.bns_s62_mp_finexam.Utility.ImageCardBig
import com.example.bns_s62_mp_finexam.Utility.JSONalamatProcessor
import com.example.bns_s62_mp_finexam.Utility.SimpleText20SPFILL
import java.net.URLDecoder


@Preview(showBackground = true)
@Composable
fun DetailsViewPreview() {
    val navController = rememberNavController()
    DetailsView(navController,"Aceh", "https://upload.wikimedia.org/wikipedia/commons/4/41/Coat_of_arms_of_Aceh.svg")
}


@Composable
fun DetailsView(
    navController: NavHostController,
    daerah: String?,
    staticImage: String?,
) {
    Column {
        HeaderBar("Alamat", false, true, navController)
        val staticImageDecoded = URLDecoder.decode(staticImage, "UTF-8")

        // Data Getter
        val daerahStr = daerah.toString()
        val appContext = AppContextProvider.getInstance().getAppContext()
        val jsonProcessor = JSONalamatProcessor(appContext, daerahStr)

        // Cant be called directly on TextCard, because it need
        // detailsProvince to set size index
        val detailsProvince = jsonProcessor.getDetailsProvince()
        val detailsAddress = jsonProcessor.getDetailsAddress()
        val detailsWebsite = jsonProcessor.getDetailsWebsite()
        val detailsPhone = jsonProcessor.getDetailsPhone()
        val detailsMail = jsonProcessor.getDetailsMail()



        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(detailsProvince.size) { index ->
                val painter: Painter = rememberAsyncImagePainter(staticImageDecoded)

                ImageCardBig(
                    painter = painter,
                    contentDescription = "Logo Province",
                    detailsProvince = detailsProvince[index],
                    detailsAddress = detailsAddress[index],
                    detailsWebsite = detailsWebsite[index],
                    detailsPhone = detailsPhone[index],
                    detailsMail = detailsMail[index],
                )
                Spacer(Modifier.height(10.dp))
            }
            // Add a Text composable after the last item
            item {
                SimpleText20SPFILL("Akhir Daftar Alamat")
            }
        }
    }
}
