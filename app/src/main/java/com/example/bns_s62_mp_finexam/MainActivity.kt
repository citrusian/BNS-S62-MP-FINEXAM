package com.example.bns_s62_mp_finexam

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bns_s62_mp_finexam.Adrress.AlamatView
import com.example.bns_s62_mp_finexam.Adrress.AlamatWilayahView
import com.example.bns_s62_mp_finexam.Adrress.Provinsi.listAlamatView
import com.example.bns_s62_mp_finexam.Adrress.Provinsi.provinsiSumateraView
import com.example.bns_s62_mp_finexam.Utility.AppContextProvider
import com.example.bns_s62_mp_finexam.ui.theme.BNSS62MPFINEXAMTheme


data class BottomNavigationnItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
)

class MainActivity : ComponentActivity() {
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPrefs = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        val appContext = applicationContext
        super.onCreate(savedInstanceState)
        AppContextProvider.initialize(applicationContext)
        setContent {
            // Fix Theme placement, so Material Color applied globally
            BNSS62MPFINEXAMTheme {
                // Move NavBar to separate func to use preview
                MainNavigationBar()
            }
        }
    }
}


//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavigationBar() {
    // set Current Screen Default
    var currentScreen by remember { mutableStateOf("Home") }
//    var currentScreen by remember { mutableStateOf("Alamat") }

    val items = listOf(
        BottomNavigationnItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
            badgeCount = null,
        ),
        BottomNavigationnItem(
            title = "Alamat",
            selectedIcon = Icons.Filled.LocationOn,
            unselectedIcon = Icons.Outlined.LocationOn,
            hasNews = false,
            badgeCount = null,
        ),
        BottomNavigationnItem(
            title = "About",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            hasNews = false,
            badgeCount = null,
        ),
    )

//    val index = items.indexOfFirst { it.title == currentScreen }
//    var selectedItemIndex = if (index != -1) index else 0

    val index = items.indexOfFirst { it.title == currentScreen }
    var selectedItemIndex by remember { mutableStateOf(if (index != -1) index else 0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Scaffold (
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                currentScreen = item.title
                            },
                            label = {
                                Text(text = item.title)
                            },
                            icon = {
                                val selectedImage = remember(selectedItemIndex, index) {
                                    if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else {
                                        item.unselectedIcon
                                    }
                                }
                                Icon(
                                    imageVector = selectedImage,
                                    contentDescription = item.title,
                                )
                            }
                        )
                    }
                }
            }
        ){
            // TODO: Refactor duplicate later
            // FIXED, basically the nav composable was rendered behind Scaffold
            val navController = rememberNavController()
            val currentRoute = navController.currentDestination?.route
            NavHost(
                navController = navController,
                startDestination = "homescreen",
            ) {
                composable("homescreen") {
                    selectedItemIndex = 0
                    HomeView(navController)
                }
                composable("aboutscreen") {
                    selectedItemIndex = 2
                    AboutView(navController)
                }
                composable("alamatscreen") {
                    selectedItemIndex = 1
                    AlamatView(navController)
                }
                composable("alamatwilayahscreen") {
                    selectedItemIndex = 1
                    AlamatWilayahView(navController)
                }

                // TODO ----------------------------------------------------
                //       WILAYAH ROUTE
                // TODO ----------------------------------------------------
                composable("provinsiSumatera") {
                    provinsiSumateraView(navController)
                }
                composable("provinsiJawa") {
                    // TODO FIX THIS LATER
                    AboutView(navController)
                }
                composable("provinsiSulawesi") {
                    // TODO FIX THIS LATER
                    AboutView(navController)
                }
                composable("provinsiKalimantan") {
                    // TODO FIX THIS LATER
                    AboutView(navController)
                }
                composable("provinsiBali") {
                    // TODO FIX THIS LATER
                    AboutView(navController)
                }
                composable("provinsiPapua") {
                    // TODO FIX THIS LATER
                    AboutView(navController)
                }

                // TODO ----------------------------------------------------
                //       WILAYAH ROUTE
                // TODO ----------------------------------------------------
                composable("listAlamat") { backStackEntry ->
                    val details = backStackEntry.arguments?.getString("details")
                    Log.d("DEBUG", "backStackEntry: $details")
                    listAlamatView(navController, details)
                }
            }

            // Use CurrentScreen
            when (currentScreen) {
                "Home" -> {
                    selectedItemIndex = 0
                    navController.navigate("homescreen")
                }
                "Alamat" -> {
                    selectedItemIndex = 1
                    navController.navigate("alamatwilayahscreen")
                }
                "About" -> {
                    selectedItemIndex = 2
                    navController.navigate("aboutscreen")
                }
            }





        }
    }
}