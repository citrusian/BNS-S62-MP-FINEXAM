package com.example.bns_s62_mp_finexam.Utility

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.bns_s62_mp_finexam.ui.theme.md_theme_light_inversePrimary


@Preview(showBackground = true)
@Composable
fun SimpleTextPreview() {
    SimpleText20SPFILL("Simple Text")
}

@Preview(showBackground = true)
@Composable
fun ListViewSimple() {

    val imageUrls = listOf(
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2F2d4b8fde-5ec2-4c72-b804-29d3cc14e3d7.799a99c1196c2fd4.gif&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75",
        "https://design.google/_next/image?url=https%3A%2F%2Fstorage.googleapis.com%2Fgd-prod%2Fimages%2Fa910d418-7123-4bc4-aa3b-ef7e25e74ae6.799a99c1196c2fd4.webp&w=1920&q=75"
    )

    val detailsProvince = listOf(
        "Wilayah Sumatera",
        "Wilayah Jawa",
        "Wilayah Sulawesi",
        "Wilayah Kalimantan",
        "Wilayah Bali, Nusa Tenggara, Maluku",
        "Wilayah Papua",
    )

    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(detailsProvince.size) { index ->
            ImageCardMedium(
                painter = rememberAsyncImagePainter(imageUrls[index]),
                contentDescription = "Logo Province",
                detailsProvince = detailsProvince[index],
            )
            Spacer(Modifier.height(10.dp))
        }

        // Add a Text composable after the last item
        item {
            SimpleText20SPFILL("Akhir Daftar Wilayah")
        }
    }
}

// TODO --------------------------------------------
//                                       PREVIEW END
// TODO --------------------------------------------

@Composable
fun SimpleText20SPFILL(
    text: String,
) {
    Text(
        text = "$text",
        style = TextStyle(
            fontSize = 20.sp
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(bottom = 80.dp)
        ,
        textAlign = TextAlign.Center
    )
}

@Composable
fun SimpleText(
    text: String,
    fontSize: Int,
    textAlign: TextAlign
) {

//        append(text)


    // Test using append text to annotated string
    // eg to make text bold using certain symbol

    val symbol = "*"
    val annotatedString = buildAnnotatedString {
        var bold = false

//        append(text)
        if (text.contains(symbol)) {
            text.split(symbol).forEach { part ->
                if (bold) {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(part)
                    }
                } else {
                    append(part)
                }
                // make bold to normal
                // remove if not used later
                bold = !bold
            }
        }
        else{
            append(text)
        }
    }

    Text(
        text = annotatedString,
        style = TextStyle(fontSize = fontSize.sp),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
        textAlign = textAlign
    )
}


@Composable
fun ClickableLinkText(url: String) {
    val uriHandler = LocalUriHandler.current

    Text(
        text = url,
        style = TextStyle(
            fontSize = 18.sp,
            color = Color.Blue
        ),
        modifier = Modifier
//            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .clickable {
                uriHandler.openUri(url)
            },
        textAlign = TextAlign.Justify
    )
}

// TODO --------------------------------------------
//                                 TEXT RENDERER END
// TODO --------------------------------------------

@Composable
fun ImageCardMedium(
    painter: Painter,
    contentDescription: String,
    detailsProvince: Any,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        // Tutorial use old command, new one in
        // https://developer.android.com/jetpack/compose/components/card#elevated
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
                        .size(height = 90.dp, width = 90.dp)
                        .clip(RoundedCornerShape(15.dp))
                )
                Text(
                    text = "$detailsProvince",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    ),
                    modifier = Modifier
                        .weight(1.4f)
                        .padding(10.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ImageCardBig(
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
                            append("\nALAMAT: \n")
                        }
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            val url2 = detailsAddress
                            withAnnotation(
                                tag = "URL",
                                annotation = url2
                            ) {
                                append(detailsAddress)
                            }
                        }
//                        append("\n$detailsAddress")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,)) {
                            append("\nWEBSITE: \n")
                        }
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            val url2 = detailsWebsite
                            withAnnotation(
                                tag = "URL",
                                annotation = url2
                            ) {
                                append(detailsWebsite)
                            }
                        }

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

@Composable
fun ClickableLinkTextInside(url: String) {
    val uriHandler = LocalUriHandler.current

    Text(
        text = url,
        style = TextStyle(
            fontSize = 18.sp,
            color = Color.Blue
        ),
        modifier = Modifier
            .clickable {
                uriHandler.openUri(url)
            },
    )
}