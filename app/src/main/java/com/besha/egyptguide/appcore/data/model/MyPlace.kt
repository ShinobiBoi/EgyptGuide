package com.besha.egyptguide.appcore.data.model

import android.net.Uri
import com.google.android.gms.maps.model.LatLng

data class MyPlace(
val id: String?,
val displayName: String?,
val formattedAddress: String?,
val location: LatLng?,
val imageUri :  Uri?,
)
