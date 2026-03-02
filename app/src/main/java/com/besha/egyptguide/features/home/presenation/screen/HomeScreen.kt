package com.besha.egyptguide.features.home.presenation.screen

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.besha.egyptguide.R
import com.besha.egyptguide.appcore.data.model.MyPlace
import com.besha.egyptguide.features.home.presenation.viewmodel.HomeActions
import com.besha.egyptguide.features.home.presenation.viewmodel.HomeViewModel
import com.besha.egyptguide.features.maps.presentaion.viewmodel.MapsActions
import com.google.android.libraries.places.api.model.Place
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(){


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
    val pagerState = rememberPagerState(pageCount = {8})


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {





        LaunchedEffect(state.location.data) {
            state.location.data?.let {
                viewModel.executeAction(HomeActions.GetHistoricalPlaces(it))
            }
        }



        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = "Hi,Guest",
                    fontSize = 30.sp,
                    color = colorResource(R.color.black)
                )
                Text(
                    text = "Experience the Eternity",
                    fontSize = 18.sp,
                    color = colorResource(R.color.gray)
                )

            }

            val profilePic= null

            if (profilePic!=null)
                AsyncImage(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(54.dp),
                    model = "https://image.tmdb.org/t/p/original${profilePic}",
                    contentDescription = "profile pic",
                    contentScale = ContentScale.Crop,
                )
            else(
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(50))
                            .background(colorResource(R.color.blue)),
                        contentAlignment = Alignment.Center
                    ) {
                        val initials = "?"
                        Text(
                            text = initials,
                            color = colorResource(R.color.white),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                    )

        }



        state.testPlaces.data?.let {
            TrendingMovieBannerPager(it.subList(0, 8),pagerState)
/*            {id,mediaTpe->
                controller.navigate(ScreenResources.DetailScreenRoute(id,mediaTpe))
            }*/
        }


        Text(
            text = "Find now",
            color = colorResource(R.color.black),
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 13.dp, top = 10.dp)
        )


      /*  when(state.mediaType.data){
            MediaType.All->{

                if (state.trendingMovies.data.isNullOrEmpty()) viewModel.executeAction(HomeAction.GetTrendingMovies)
                if (state.trendingTv.data.isNullOrEmpty())viewModel.executeAction(HomeAction.GetTrendingTv)
                if (state.trendingPeople.data.isNullOrEmpty())viewModel.executeAction(HomeAction.GetTrendingPeople)

                HomeMoviesList(state.trendingMovies, title = "Trending movies"){ id,type ->
                    controller.navigate(ScreenResources.DetailScreenRoute(id,type))

                }

                HomeMoviesList(state.trendingTv, title = "Trending tv series"){ id,type ->
                    controller.navigate(ScreenResources.DetailScreenRoute(id,type))

                }

                HomeMoviesList(state.trendingPeople, title = "Trending people"){ id,type ->
                    controller.navigate(ScreenResources.DetailScreenRoute(id,type))

                }
            }
            MediaType.Movies->{
                if (state.popularMovies.data.isNullOrEmpty()) viewModel.executeAction(HomeAction.GetPopularMovies)
                if (state.topRatedMovies.data.isNullOrEmpty())viewModel.executeAction(HomeAction.GetTopRatedMovies)
                if (state.upComingMovies.data.isNullOrEmpty()) viewModel.executeAction(HomeAction.GetUpComingMovies)





                HomeMoviesList(state.trendingMovies, title = "Trending movies"){ id,type ->
                    controller.navigate(ScreenResources.DetailScreenRoute(id,type))

                }

                HomeMoviesList(state.popularMovies, title = "Popular movies"){ id,type ->

                    controller.navigate(ScreenResources.DetailScreenRoute(id,type))

                }

                HomeMoviesList(state.topRatedMovies, title = "Top rated movies"){ id,type ->

                    controller.navigate(ScreenResources.DetailScreenRoute(id,type))

                }

                HomeMoviesList(state.upComingMovies, title = "Upcoming movies"){ id,type ->
                    controller.navigate(ScreenResources.DetailScreenRoute(id,type))

                }
            }
            MediaType.Tv->{
                if (state.onTheAirTv.data.isNullOrEmpty())viewModel.executeAction(HomeAction.GetOnTheAirTv)
                if (state.topRatedTv.data.isNullOrEmpty()) viewModel.executeAction(HomeAction.GetTopRatedTv)
                if (state.popularTv.data.isNullOrEmpty()) viewModel.executeAction(HomeAction.GetPopularTv)


                HomeMoviesList(state.onTheAirTv, title = "On the air tv series"){ id,type ->
                    controller.navigate(ScreenResources.DetailScreenRoute(id,type))

                }
                HomeMoviesList(state.trendingTv, title = "Trending tv series"){ id,type ->
                    controller.navigate(ScreenResources.DetailScreenRoute(id,type))

                }
                HomeMoviesList(state.topRatedTv, title = "Top rated tv series"){ id,type ->
                    controller.navigate(ScreenResources.DetailScreenRoute(id,type))

                }
                HomeMoviesList(state.popularTv, title = "Popular tv series"){ id,type ->
                    controller.navigate(ScreenResources.DetailScreenRoute(id,type))

                }

            }
            MediaType.People ->{
                HomeMoviesList(state.trendingPeople, title = "Trending people"){ id,type ->
                    controller.navigate(ScreenResources.DetailScreenRoute(id,type))

                }

            }
            else -> {}
        }
*/

    }


}

@Composable
fun TrendingMovieBannerPager(
    placesList: List<MyPlace>,
    pagerState: PagerState,
    //onItemClick: (Int,MediaType) -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    // Auto-slide logic
    LaunchedEffect(Unit) {
        while (true) {
            val currentPage = pagerState.currentPage
            delay(5000)
            if (placesList.isNotEmpty()&&currentPage==pagerState.currentPage) {
                val nextPage = (pagerState.currentPage + 1) % placesList.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    // ---- The pager with overlay text ----
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        val place = placesList[page]

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.3f)
                .padding(horizontal = 13.dp, vertical = 24.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable(
                    onClick = {
                         //onItemClick(mediaItem.id,mediaItem.media_type)
                         }
                )
        ) {
            // Movie image
            AsyncImage(
                model = place.imageUri,
                contentDescription = place.displayName,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            // Gradient overlay (for better text visibility)
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                        )
                    )
            )

            // Text overlay (title + tag)
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(colorResource(R.color.blue))
                        .padding(4.dp),
                    text = "Visit Now",
                    color = colorResource(R.color.white),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = place.displayName?:"",
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.basicMarquee()
                )

                val currentPage = pagerState.currentPage
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    repeat(placesList.size) { index ->
                        val isSelected = currentPage == index
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(if (isSelected) 10.dp else 8.dp)
                                .clip(RoundedCornerShape(50))
                                .background(if (isSelected) colorResource(R.color.white) else colorResource(R.color.gray))
                        )
                    }
                }
            }
        }
    }
}