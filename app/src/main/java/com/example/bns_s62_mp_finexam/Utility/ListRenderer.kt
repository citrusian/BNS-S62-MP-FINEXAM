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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.bns_s62_mp_finexam.R
import java.net.URLEncoder

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
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
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
                // Handle opening Google Maps and Official Website
                val uriHandler = LocalUriHandler.current
                val sanitizedAddress = URLEncoder.encode(detailsAddress, "UTF-8")
                val AddressURL = "https://maps.google.com/maps?q=$sanitizedAddress"
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .heightIn(0.dp, 150.dp)
                        .weight(0.6f)
                )
                Column (
                    modifier = Modifier
                        .weight(1.4f)
                        .padding(10.dp)
                ) {
                    // Note !! using specific text splitting to get better clickable area
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("PROVINSI: ")
                            }
                            append("\n$detailsProvince")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,)) {
                                append("\nALAMAT: ")
                            }
                        },
                    )
                    // TODO -----------------------------------------------
                    //                                     Alamat Hyperlink
                    // TODO -----------------------------------------------
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append(detailsAddress)
                            }
                        },
                        modifier = Modifier
                            .clickable {
                                uriHandler.openUri(AddressURL)
                            }
                    )
                    // TODO -----------------------------------------------
                    //                                    Website Hyperlink
                    // TODO -----------------------------------------------
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,)) {
                                append("WEBSITE: ")
                            }
                        },
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                    append(detailsWebsite)
                            }
                        },
                        modifier = Modifier
                            .clickable {
                                uriHandler.openUri(detailsWebsite)
                            }
                    )
                    // TODO -----------------------------------------------
                    //                                            TEl EMAIL
                    // TODO -----------------------------------------------
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("TEL/FAX: ")
                            }
                            append("\n$detailsPhone")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("\nE-MAIL: ")
                            }
                            append("\n$detailsMail")
                        },
                    )
                    // TODO -----------------------------------------------
                    //                                           Column END
                    // TODO -----------------------------------------------
                }
            }
        }
    }
}

@Composable
fun DataListRenderer(
    navController: NavHostController,
    detailsDaerah: List<Any>,
    imageUrls: List<Any>,
    destinationMap: Any? = null
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(detailsDaerah.size) { index ->
            val defaultDrawable = R.drawable.baseline_error_outline_24
            val item = imageUrls.getOrNull(index) ?: defaultDrawable
            val encodedItem = URLEncoder.encode(item.toString(), "UTF-8")
            val imageType = determineImageType(item)

            val painter: Painter = when (imageType) {
                ImageType.DrawableResource -> rememberAsyncImagePainter(item)
                ImageType.URL -> rememberAsyncImagePainter(item)
                ImageType.Unknown -> painterResource(defaultDrawable)
            }

            val details = detailsDaerah[index]

            val (destinationFlag, destination) = when (destinationMap) {
                is Map<*, *> -> true to (destinationMap as? Map<*, *>)?.get(details)
                is String -> false to destinationMap
                else -> false to "homescreen"
            }

            // Check if Destination has 1 or 2 passable
            if (destinationFlag) {
                ImageCardMedium(
                    painter = painter,
                    contentDescription = "Logo Province",
                    detailsProvince = details,
                    modifier = Modifier
                        .clickable {
                            destination?.let { route ->
                                navController.navigate("listProvinsi/$details")
                            }
                        }
                )
            } else {
                ImageCardMedium(
                    painter = painter,
                    contentDescription = "Logo Province",
                    detailsProvince = details,
                    modifier = Modifier
                        .clickable {
                            destination?.let { route ->
                                navController.navigate("listAlamat/$details/$encodedItem")
                            }
                        }
                )
            }
            Spacer(Modifier.height(10.dp))
        }
        item {
            SimpleText20SPFILL("End Of List")
        }
    }
}