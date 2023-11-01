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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar

@Preview(showBackground = true)
@Composable
fun AlamatView() {
    Column {
        HeaderBar("Alamat", true)
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

    val imageDesc = listOf(
        "Google Doodle Desc ",
        "Google Doodle Desc ",
        "Google Doodle Desc ",
        "Google Doodle Desc ",
    )

    val imageTitles = listOf(
        "Google Doodle 1",
        "Google Doodle 2",
        "Google Doodle 3",
        "Google Doodle 4",
    )

    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(imageUrls.size) { index ->
            ImageCard(
                painter = rememberAsyncImagePainter(imageUrls[index]),
                contentDescription = "${imageDesc[index]} ${index + 1}",
                // Test using index to call desc, and call index number +1
                // title = "Google Doodle ${index + 1}",
                title = imageTitles[index],
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
//                .height(200.dp)
                // Test using min-max height
                .heightIn(0.dp, 200.dp)
            ,
        ){
            // Image and Text box, use Row Arrangement
            Row (
            ){
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    //contentScale = ContentScale.Crop,
                    // Fit show better output
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(height = 200.dp, width = 100.dp)
                        .weight(1f)
                )
                Text(
                    text = "test",
                    modifier = Modifier.weight(1.4f)
                )
            }
            // Gradient Black Bar
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        // Hardcoded Gradient Size
                        startY = 450f

                    )
                )
            )
            // Text inside Gradient bar
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                ,
                contentAlignment = Alignment.BottomStart
            ){
                Text(
                    title,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}