package com.example.bns_s62_mp_finexam

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar


@Preview(showBackground = true)
@Composable
fun AboutViewPreview() {
    val navController = rememberNavController()
    AboutView(navController)
}

@Composable
fun AboutView(navController: NavHostController) {
    // TODO: About
    Column {
        HeaderBar("About", false, true, navController)
        Text(
            text = "ABOUT PAGE, test, test ,test"
            ,
            style = TextStyle(
                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
            )
        )
    }
}