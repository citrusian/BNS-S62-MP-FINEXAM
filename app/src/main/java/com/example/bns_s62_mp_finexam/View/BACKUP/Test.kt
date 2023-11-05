//package com.example.bns_s62_mp_finexam.View.BACKUP
//
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar
import com.example.bns_s62_mp_finexam.Utility.SimpleText20SPFILL
import com.example.bns_s62_mp_finexam.View.BACKUP.AdrressListView


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun AlamatViewPreview() {
    val navController = rememberNavController()
    AlamatView(navController)
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AlamatView(navController: NavHostController) {
    Column {
        HeaderBar("Alamat", true, false, navController)
        AdrressListView(navController)
    }
}

@Composable
fun AdrressListViewTest(navController: NavHostController) {

    val imageUrls = listOf(
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2F2d4b8fde-5ec2-4c72-b804-29d3cc14e3d7.799a99c1196c2fd4.gif&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75"
    )

    val locationList = listOf(
        "Location 1 ",
        "Location 2 ",
        "Location 3 ",
        "Location 4 ",
    )

    val detailsProvince = listOf(
        "Aceh",
        "D.I Yogyakarta",
        "Google",
        "Google",
    )

    val detailsAddress = listOf(
        "JL. T. Nyak Arief No.219, Banda Aceh (23114)",
        "JL. Malioboro No.16, Suryatmajan, Komplek Kepatihan  Danurejan, Kota Yogyakarta (55213)",
        "Google",
        "Google",
    )

    val detailsWebsite = listOf(
        "www.acehprov.go.id",
        "www.jogjaprov.go.id",
        "Google",
        "Google",
    )

    val detailsPhone = listOf(
        "T: (0651)-7551377  F: (0651)-7552307,  7555516, 7555518, 7553048,",
        "(0274)-562811,  512655",
        "Google",
        "Google",
    )

    val detailsMail = listOf(
        "humas@acehprov.go.id",
        "null",
        "Google",
        "Google",
    )

    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(imageUrls.size) { index ->
            ImageCardBigTEST(
                painter = rememberAsyncImagePainter(imageUrls[index]),
//                contentDescription = "${imageDesc[index]} ${index + 1}",
                contentDescription = "Logo Province",
                // Test using index to call desc, and call index number +1
                // title = "Google Doodle ${index + 1}",
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

@Composable
fun ImageCardBigTEST(
    painter: Painter,
    contentDescription: String,
    detailsProvince: String,
    detailsAddress: String,
    detailsWebsite: String,
    detailsPhone: String,
    detailsMail: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ){
        Box(modifier = Modifier.fillMaxSize())
        {
            Row (verticalAlignment = Alignment.CenterVertically)
            {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .heightIn(0.dp, 150.dp)
                        .weight(0.6f)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("PROVINSI: ")
                        }
                        append("\n$detailsProvince")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("\nALAMAT: ")
                        }
                        append("\n$detailsAddress")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("\nWEBSITE: ")
                        }
                        append("\n$detailsWebsite")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("\nTEL/FAX: ")
                        }
                        append("\n$detailsPhone")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("\nE-MAIL: ")
                        }
                        append("\n$detailsMail")
                    },
                    modifier = Modifier
                        .weight(1.4f)
                        .padding(10.dp)
                )
            }
        }
    }
}
