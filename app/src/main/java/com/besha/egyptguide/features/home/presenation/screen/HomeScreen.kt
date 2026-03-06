package com.besha.egyptguide.features.home.presenation.screen

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.besha.egyptguide.R
import com.besha.egyptguide.appcore.data.model.MyPlace
import com.besha.egyptguide.features.home.presenation.viewmodel.HomeActions
import com.besha.egyptguide.features.home.presenation.viewmodel.HomeViewModel
import com.google.android.gms.maps.model.LatLng
import androidx.core.net.toUri


@Composable
fun HomeScreen() {


    val egyptPlaces = listOf(
        MyPlace(
            id = "ChIJzbs54n1PWBQRizZuWjV0dMo",
            displayName = "Giza Necropolis",
            formattedAddress = "Al Haram, Giza Governorate 3512201, Egypt",
            location = LatLng(29.977296199999998, 31.132495499999997),
            imageUri = "https://lh3.googleusercontent.com/place-photos/AL8-SNHTs79nKF_Ci53fecc1SDIU_pKslB1dowWA-6-LrcEQ84HEpx7urXmpLdyGEZJwlK3yYfQuBktC1XBTR_L9j6G2ThMn95X9wi3Ks0SHPUkqEVnsZklItA_blnuBORtuz7wWTvOPSCsFqEBkXw=s4800-w4800-h4408".toUri()
        ),
        MyPlace(
            id = "ChIJeamuo2JPWBQRqb1mQKbw08k",
            displayName = "Great Sphinx of Giza",
            formattedAddress = "Al Haram, Giza Governorate 3512201, Egypt",
            location = LatLng(29.9752687, 31.1375674),
            imageUri = "https://lh3.googleusercontent.com/place-photos/AL8-SNEeW1LMSp4BhK7CxcITKqQzy82zSD02gKNvaI18TaswMFsrDiw_Ht5snGPa1sLtU25eANaUEm9ilashVAuGCHHQAGW-FfpJLhq5vfed3UGR2P9Vio4tSgNEfSra1lHBX5A9cvqFayfZ02TL=s4800-w4000-h3000".toUri()
        ),
        MyPlace(
            id = "ChIJzcD-KJIVSRQR2FnCCMDoGsc",
            displayName = "Karnak",
            formattedAddress = "Karnak, Luxor, Luxor Governorate, Egypt",
            location = LatLng(25.718834599999997, 32.6572703),
            imageUri = "https://lh3.googleusercontent.com/place-photos/AL8-SNGpUEBYi6BrzXCEHSVUxFDvmfqsasbQini4nSVirXlyej8YA929CwnO700XuhMTcB9HL4M6pHkEJWX5y81MkWVNTrSlxgYjC4gpLaVMPcj-hqx-qIFlEv4UEb2rzlpdm0f7pNUWA-hVMThI9w=s4800-w4032-h3024".toUri()
        ),
        MyPlace(
            id = "ChIJ1_7etYo9SRQR2qnjovbMj3E",
            displayName = "Valley of the Kings",
            formattedAddress = "Luxor, Luxor Governorate 1340420, Egypt",
            location = LatLng(25.7401643, 32.601411),
            imageUri = "https://lh3.googleusercontent.com/place-photos/AL8-SNHfcPPIErOnbvCM0uIuuY_Fcr9cMgr-9tk6mryibZc5cmTpsnYix8Kwsiv-uGxYI1NzXD2NfdTwnVvNCYmb78OtgaXzjVvKCUvrEoSa6773M-nmC2WusqDSz9FlkTNDt3SSxYmREgxGSida5Mc=s4800-w4000-h3000".toUri()
        ),
        MyPlace(
            id = "ChIJ3ae_xxgWSRQR07VjFXrhHwM",
            displayName = "Mortuary Temple of Hatshepsut",
            formattedAddress = "Luxor, Al Qarna, Luxor Governorate 1340420, Egypt",
            location = LatLng(25.738277300000004, 32.6064906),
            imageUri = "https://lh3.googleusercontent.com/place-photos/AL8-SNHFK1z45jyqMi5nfkeXML3B3uknXorhCq6u7aA12CUHdh8NwyYY7S66SNXmCWsSBkvS_fJzXN7Q39KyI3uoRhaPhgKz1ao3jHz8MZbrVipg9Xj3P7RRphNKjj3uuOrEnglf-Nku5NG-ta-b=s4800-w2048-h1431".toUri()
        ),
        MyPlace(
            id = "ChIJn1b3gKpAWBQRbsg1WfPe1NA",
            displayName = "Mosque of Muhammad Ali",
            formattedAddress = "Salah al-Din, al-Ayyubi, Saladin, Cairo Governorate 4252360, Egypt",
            location = LatLng(30.0290456, 31.259796599999998),
            imageUri = "https://lh3.googleusercontent.com/place-photos/AL8-SNHH6_RKeEJW5fB6PYoAbN-g_yZpRFgESGrAo_-wqJ13sZOOrbVM85B95hjXOKEzwovfO2bd_iNdm8v2f94pq-tjYdZzbAakcH3T7sFFaJL4ekvhjICBVHZL26nDQPLkOYO-FOgOq3S2BWwtcQ=s4800-w2689-h2477".toUri()
        ),
        MyPlace(
            id = "ChIJn3WoRhBHWBQRuhsCgung4tQ",
            displayName = "Hanging Church",
            formattedAddress = "مار جرجس، محطة مترو الأنفاق(مارجرجس, Ibrahim Ali, Kom Ghorab, Old Cairo, Cairo Governorate 4244001, Egypt",
            location = LatLng(30.0052389, 31.2301689),
            imageUri = "https://lh3.googleusercontent.com/place-photos/AL8-SNH_J_S4OQOnVnwiuzw0baAuhc289z6lFaqw5zwNXBtCvk9j3k3r3gVBPvg6t46bXRFlV1Pdcc2HuX-i-0mub6ZNzPBLQMe8oPdUNd5kNGQXCQZnhvobc9N3A87uQvVx6ngCq46IiRfRspB_sf4=s4800-w529-h398".toUri()
        ),
        MyPlace(
            id = "ChIJuxEpKFLD9RQRHz2y-AiDn-U",
            displayName = "Qaitbay Citadel",
            formattedAddress = "السيالة شرق، قسم الجمرك،، As Sayalah Sharq, Al Gomrok, Alexandria Governorate 5321431, Egypt",
            location = LatLng(31.213698700000002, 29.8853921),
            imageUri = "https://lh3.googleusercontent.com/place-photos/AL8-SNEdOFZSryJLlEVUp4crTjTnwaXpmgFbHrdkhmvEl0Lcv7NYFZlVDEPL05t_EaLbGGHZLte0yGjmkHuGRhvLv93gPy4U2vJxM-Od4yy7HlfrYDWbTPpGVQ2duaKPBLDoHWNgshsyCgUVRR5cIA=s4800-w4800-h3600".toUri()
        ),
        MyPlace(
            id = "ChIJuXeJLNtiNhQReOUrg0ms8pk",
            displayName = "Philae Temple",
            formattedAddress = "Aswan 1, البحر الأحمر 1240271, Egypt",
            location = LatLng(24.025583599999997, 32.8841021),
            imageUri = "https://lh3.googleusercontent.com/place-photos/AL8-SNFsI17xNawXiX8J8maxEy7QHda11H3K7bK0Z0YGWYnP73-SF5EGUbSiSxh59dl0XKu4vvwXKhnhjBRZjinDCUEeIe4axcgvzsmipx9bvSyBX9JEUuqYBNQUPBlY-6jSX5NacPdVexVYLS-YyA=s4800-w4032-h3024".toUri()
        ),
        MyPlace(
            id = "ChIJWwUmsYipOhQR0pj4GGbM06c",
            displayName = "Abu Simbel Temples",
            formattedAddress = "Abu Simbel, Aswan Governorate 1211501, Egypt",
            location = LatLng(22.3372319, 31.625798999999997),
            imageUri = "https://lh3.googleusercontent.com/place-photos/AL8-SNHqdmKD6AvN9mShclZ7s6HE_en5qWOCW9q3H8D78bMQnD5z9LYqHopMcWRbjqoeg1phqRTs8zOuSg5LLytxLE5yRfgm3DxC6zJXLrod80bm-VT21LF5SXSiXwXnyWn0rerEtQ8jnpD8Z-6MpA=s4800-w4800-h3614".toUri()
        )
    )

    val viewModel = hiltViewModel<HomeViewModel>()

    val state by viewModel.viewStates.collectAsState()

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            viewModel.executeAction(HomeActions.GetCurrentLocation)
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }


    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(pageCount = { 8 })


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {


/*        LaunchedEffect(state.location.data) {
            state.location.data?.let {
                if (state.historicalPlaces.data.isNullOrEmpty())
                    viewModel.executeAction(HomeActions.GetHistoricalPlaces(it))
                if (state.hotelPlaces.data.isNullOrEmpty())
                    viewModel.executeAction(HomeActions.GetHotelPlaces(it))
                if (state.restaurantPlaces.data.isNullOrEmpty())
                    viewModel.executeAction(HomeActions.GetRestaurantsPlaces(it))
                if (state.attractionsPlaces.data.isNullOrEmpty())
                    viewModel.executeAction(HomeActions.GetAttractionsPlaces(it))
            }
        }*/



        state.attractionsPlaces.data?.let {


        }


        Text(
            text = stringResource(R.string.near_by_locations),
            color = colorResource(R.color.black),
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 13.dp, top = 10.dp)
        )


        PlacesGridList(places = egyptPlaces) {

        }

    }

}






@Composable
fun PlacesGridList(
    modifier: Modifier=Modifier,
    places: List<MyPlace>,
    onItemClick: () -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(places) { poster ->


            Column (modifier = Modifier.clickable(
                onClick = {
                    onItemClick()
                }
            )){
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .size(width = 184.dp, height = 244.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        model = poster.imageUri,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,


                        )

                    Text(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(top = 10.dp),
                        text = poster.displayName ?: "",
                        overflow = TextOverflow.Ellipsis,
                        color = colorResource(R.color.black)
                    )
                }


            }


        }
    }
}


