package com.example.bns_s62_mp_finexam.View

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bns_s62_mp_finexam.Navigation.HeaderBar
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Preview(showBackground = true)
@Composable
fun MapViewPreview() {
    val navController = rememberNavController()
    MapView(navController, "-6.171997999999999, 106.81980809999999", "JAKARTA")
}

// TODO: NOTE: https://developers.google.com/maps/documentation/geocoding/overview
@Composable
fun MapView(
    navController: NavHostController,
    geocodedAddress : String?,
    details : String?,
) {
    Column {
        HeaderBar("Peta Provinsi $details", false, true, navController)
        val titles = details.toString()
        var Latitude = 0.0
        var Longitude = 0.0
        // split string before sending to GoogleMapsView
        val parts = geocodedAddress?.split(", ")
        if (parts != null) {
            if (parts.size == 2) {
                val lat = parts[0].toDoubleOrNull()
                val lng = parts[1].toDoubleOrNull()
                if (lat != null && lng != null) {
                    Latitude = lat
                    Longitude = lng
                    Log.d("DEBUG", "Latitude: $Latitude, Longitude: $Longitude")
                } else {
                    Log.e("ERROR", "Invalid lat or long")
                }
            } else {
                Log.e("ERROR", "geocodedAddress Invalid")
            }
        }
        GoogleMapsView(LatLng(Latitude, Longitude), titles)
    }
}

@Composable
fun GoogleMapsView(userLocation: LatLng, titles: String) {
    Log.d("GoogleMapsView Enter", "$userLocation")
    val updatedUserLocation by rememberUpdatedState(userLocation)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(updatedUserLocation, 17f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        ) {
        Marker(
            state = MarkerState(position = updatedUserLocation),
            title = "Kantor Gubernur Provinsi $titles",
            draggable = false,
        )
        Log.d("GoogleMapsView Exit", "$updatedUserLocation")
    }
}