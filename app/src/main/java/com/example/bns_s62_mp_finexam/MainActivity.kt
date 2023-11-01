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
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.bns_s62_mp_finexam.ui.theme.BNSS62MPFINEXAMTheme


data class BottomNavigationnItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Fix Theme placement, so Material Color applied globally
            BNSS62MPFINEXAMTheme {
                // Move NavBar to separate func to use preview
                MainNavigationBar()
            }
        }
    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavigationBar() {
    // set Current Screen Default to Home
    var currentScreen by remember { mutableStateOf("Home") }

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

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)

    }
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
                                // TODO: Add Nav Controller with respective views
                                // navController.navigate(item.title)
//                                    currentScreen = item.title
//                                    currentScreen = items[index].title
                                currentScreen = item.title
                            },
                            label = {
                                Text(text = item.title)
                            },
                            icon = {
                                // PLACEHOLDER, not used in this application
                                // used as badge number or news icon checker
                                BadgedBox(
                                    badge = {
                                        if(item.badgeCount != null){
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews){
                                            Badge ()
                                        }

                                    }
                                ) {
                                    Icon(
                                        // Check whether the button is
                                        // selected using index
                                        imageVector = if (index == selectedItemIndex){
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title,
                                    )
                                }
                            }
                        )
                    }
                }
            }
        ){
            // Use CurrentScreen
            when (currentScreen) {
//                    "Home" -> Home.HomeScreen()
                "Home" -> HomeView()
                "Alamat" -> AlamatView()
                "About" -> AboutView()
            }
        }
    }
}
