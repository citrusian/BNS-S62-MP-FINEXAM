package com.example.bns_s62_mp_finexam.Navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Preview(showBackground = true)
@Composable
fun HeaderBarPrev() {
    val navController = rememberNavController()
    HeaderBar("Map", false, true, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderBar(
    title: String,
    searchFlag: Boolean,
    backFlag: Boolean,
    navController: NavHostController,
) {
    var searchText by remember { mutableStateOf("") }
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        /* TODO: remember to implement this,
                 disable if there is not enough time */
        actions = {
            if (searchFlag) {
                SearchBar(searchText) {
                    // TODO: add search func
                    searchText = it
                }
            }
            if (backFlag) {
                BackButton(navController)
            }
        }
    )
}

@Composable
fun BackButton(navController: NavHostController) {
    IconButton(
        onClick = {
            navController.navigateUp()
        }
    ) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
    }
}


// TODO : Add later if there is still time
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchText: String, onSearch: (String) -> Unit) {
    TextField(
        value = searchText,
        onValueChange = {
            onSearch(it)
        },
        modifier = Modifier
            .width(150.dp)
            .height(50.dp)
        ,
        placeholder = {
            Text(
                text = "Search",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    )
    IconButton(
        onClick = {
            // TODO: Search Function
        }
    ) {
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
    }
}