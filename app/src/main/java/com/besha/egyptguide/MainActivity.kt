package com.besha.egyptguide

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.besha.egyptguide.appcore.data.remote.BackEndServices
import com.besha.egyptguide.appcore.navigation.ScreenResources
import com.besha.egyptguide.auth.presentaion.screen.AurhScreen
import com.besha.egyptguide.features.main.presentaion.screen.MainScreen
import com.besha.egyptguide.ui.theme.EgyptguideTheme
import com.google.android.gms.maps.GoogleMap
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var backEndServices: BackEndServices


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContent {
            EgyptguideTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

/*

                    LaunchedEffect(Unit) {
                        val users=backEndServices.getUsers()
                        Log.d("users",users.toString())
                        Log.d("users",users.body()?.get(0).toString())
                    }
*/



                    NavHost(
                        navController,
                        startDestination = ScreenResources.MainRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {


                        composable<ScreenResources.AuthRoute> {

                            AurhScreen(navController)


                        }

                        composable <ScreenResources.MainRoute>{
                            MainScreen(navController)
                        }


                    }
                }
            }
        }
    }


}