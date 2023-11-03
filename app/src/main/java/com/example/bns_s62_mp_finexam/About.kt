package com.example.bns_s62_mp_finexam

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar
import com.example.bns_s62_mp_finexam.Utility.ClickableLinkText
import com.example.bns_s62_mp_finexam.Utility.SimpleText


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
        Spacer(Modifier.height(10.dp))
        SimpleText(
            "Aplikasi ini dibuat sebagai hasil\n" +
                    "Ujian BINUS Mobile Programming\n" +
                    "\n\n" +
                    "Source Code:"
        ,20, TextAlign.Justify)
        ClickableLinkText("https://github.com/citrusian/BNS-S62-MP-FINEXAM")
        Spacer(Modifier.height(20.dp))
        SimpleText("- RAF -",20, TextAlign.Justify)
    }
}

