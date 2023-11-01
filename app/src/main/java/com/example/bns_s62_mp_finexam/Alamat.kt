package com.example.bns_s62_mp_finexam

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar

@Preview(showBackground = true)
@Composable
fun AlamatView() {
    Column {
        HeaderBar("Alamat", true, false)
        AdrressListView()
    }
}


// https://www.youtube.com/watch?v=KPVoQjwmWX4
// Tutorial

// using LazyColumn, eg: RecycleView
@Preview(showBackground = true)
@Composable
fun AdrressListView() {

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
            ImageCard(
                painter = rememberAsyncImagePainter(imageUrls[index]),
//                contentDescription = "${imageDesc[index]} ${index + 1}",
                contentDescription = "Logo Province",
                // Test using index to call desc, and call index number +1
                // title = "Google Doodle ${index + 1}",
                title = locationList[index],
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
            Text(
                text = "Akhir Daftar Alamat",
                style = TextStyle(
                    fontSize = 20.sp
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .padding(bottom = 100.dp)
                ,
                textAlign = TextAlign.Center
            )
        }
    }
}



@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
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
        // Tutorial use old command, new one in
        // https://developer.android.com/jetpack/compose/components/card#elevated
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
    ){
        Box(
            modifier = Modifier
//                 Test using min-max height
//                .heightIn(0.dp, 350.dp)
                .fillMaxSize()
            ,
        ){

            // Image and Text box, use Row Arrangement
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    //contentScale = ContentScale.Crop,
                    // Fit show better output
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
//                        .size(height = 200.dp, width = 100.dp)
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

            // Disabled, problematic because the content has text
            // Gradient Black Bar
//            Box(modifier = Modifier
//                .fillMaxSize()
//                .background(
//                    brush = Brush.verticalGradient(
//                        colors = listOf(
//                            Color.Transparent,
//                            Color.Black
//                        ),
//                        // Hardcoded Gradient Size
//                        startY = 850f
//
//                    )
//                )
//            )
//            // Text inside Gradient bar
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(12.dp)
//                ,
//                contentAlignment = Alignment.BottomStart
//            ){
//                Text(
//                    title,
//                    style = TextStyle(
//                        color = Color.White,
//                        fontSize = 16.sp
//                    )
//                )
//            }
        }
    }
}