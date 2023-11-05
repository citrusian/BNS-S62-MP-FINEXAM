package com.example.bns_s62_mp_finexam

import android.annotation.SuppressLint
import android.os.Bundle
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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bns_s62_mp_finexam.Utility.AppContextProvider
import com.example.bns_s62_mp_finexam.Utility.BottomNavigationItem
import com.example.bns_s62_mp_finexam.View.DetailsView
import com.example.bns_s62_mp_finexam.View.ProvinsiView
import com.example.bns_s62_mp_finexam.View.WilayahView
import com.example.bns_s62_mp_finexam.ui.theme.BNSS62MPFINEXAMTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        val appContext = applicationContext
        super.onCreate(savedInstanceState)
        AppContextProvider.getInstance().initialize(appContext)
        setContent {
            BNSS62MPFINEXAMTheme {
                MainNavigationBar()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavigationBar() {
    var currentScreen by remember { mutableStateOf("Home") }
    val items = StaticNavigationItems()
    val index = items.indexOfFirst { it.title == currentScreen }
    var selectedItemIndex by remember { mutableStateOf(if (index != -1) index else 0) }


    val navController = rememberNavController()
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
            Navigation(navController, currentScreen)
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, currentScreen: String) {
    NavHost(navController = navController, startDestination = "homescreen") {
        composable("homescreen") { HomeView(navController) }
        composable("alamatscreen") { WilayahView(navController) }
        composable("aboutscreen") { AboutView(navController) }

        // WILAYAH ROUTE
        composable(
            route = "listProvinsi/{details}",
            arguments = listOf(navArgument("details") { type = NavType.StringType })
        ) { backStackEntry ->
            val details = backStackEntry.arguments?.getString("details")
            ProvinsiView(navController, details)
        }

        // DETAILS ROUTE
        composable(
            route = "listAlamat/{details}/{encodedItem}",
            arguments = listOf(
                navArgument("details") { type = NavType.StringType },
                navArgument("encodedItem") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val details = backStackEntry.arguments?.getString("details")
            val staticImage = backStackEntry.arguments?.getString("encodedItem")
            DetailsView(navController, details, staticImage)
        }
    }

    when (currentScreen) {
        "Home" -> navController.navigate("homescreen")
        "Alamat" -> navController.navigate("alamatscreen")
        "About" -> navController.navigate("aboutscreen")
    }
}

@Composable
fun StaticNavigationItems(): List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        BottomNavigationItem(
            title = "Alamat",
            selectedIcon = Icons.Filled.LocationOn,
            unselectedIcon = Icons.Outlined.LocationOn,
        ),
        BottomNavigationItem(
            title = "About",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
        ),
    )
}