package com.besha.egyptguide.features.profile.presenation.screen

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.besha.egyptguide.R
import com.besha.egyptguide.appcore.navigation.ScreenResources
import com.besha.egyptguide.features.profile.presenation.viewmodel.ProfileActions
import com.besha.egyptguide.features.profile.presenation.viewmodel.ProfileViewModel


@Composable
fun ProfileScreen(rootController: NavController, childController: NavController) {

    val context = LocalContext.current
    val profileViewModel = hiltViewModel<ProfileViewModel>()

    val state by profileViewModel.viewStates.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.executeAction(ProfileActions.GetProfile)
    }

    /*    LaunchedEffect(state.loggedOut) {
            if (state.loggedOut.isSuccess) {



        }*/


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Account Section
        Text(
            text = "Account",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = colorResource(R.color.black)
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white)),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    val profilePic = state.profile.data?.photoUrl


                    if (profilePic != null)
                        AsyncImage(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(54.dp),
                            model = profilePic,
                            contentDescription = "profile pic",
                            contentScale = ContentScale.Crop,
                        )
                    else
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(50))
                                .background(colorResource(R.color.blue)),
                            contentAlignment = Alignment.Center
                        ) {
                            val initials =
                                state.profile.data?.username?.take(1)?.uppercase() ?: "?"
                            Text(
                                text = initials,
                                color = colorResource(R.color.white),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }


                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Welcome,",
                            color = colorResource(R.color.black),
                            fontSize = 14.sp
                        )
                        Text(
                            text = state.profile.data?.username ?: "Guest",
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.black),
                            fontSize = 16.sp
                        )
                    }
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Logout",
                    tint = colorResource(R.color.black),
                    modifier = Modifier.clickable(
                        onClick = {
                            profileViewModel.executeAction(ProfileActions.LogOut)
                            rootController.navigate(ScreenResources.AuthRoute) {
                                popUpTo(ScreenResources.MainRoute) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    )
                )
            }
        }

        Text(
            text = "List",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = colorResource(R.color.black)
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white)),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                SettingRow("Tickets") {
                    // childController.navigate(ScreenResources.WatchListScreenRoute)
                }
                HorizontalDivider(color = colorResource(R.color.gray))
                SettingRow("Favorites") {
                    // childController.navigate(ScreenResources.FavouritesScreenRoute)
                }
            }
        }
    }


}


@Composable
fun SettingRow(title: String, value: String? = null, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, color = colorResource(R.color.black), fontSize = 15.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (value != null) {
                Text(
                    text = value,
                    color = colorResource(R.color.gray),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                tint = colorResource(R.color.gray),
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
fun SettingRowSwitch(
    title: String,
    description: String = "",
    checked: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = title, color = colorResource(R.color.black), fontSize = 15.sp)
            if (description.isNotEmpty()) Text(
                text = description,
                color = colorResource(R.color.gray),
                fontSize = 7.sp
            )
        }
        Switch(checked = checked, onCheckedChange = onToggle)
    }
}

@Composable
fun NotificationPermissionToggle(
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val activity = LocalContext.current as? Activity

    // Launcher to request notification permission (Android 13+)
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            // Permission granted, update toggle state
            onToggle(true)
        } else {
            // Permission denied
            Toast.makeText(context, "Notifications are disabled", Toast.LENGTH_SHORT).show()
            onToggle(false)
        }
    }

    SettingRowSwitch(
        title = "Notification",
        description = "Enable notifications to receive your daily trending reminder.",
        checked = isEnabled,
        onToggle = { checked ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val granted = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

                if (!granted && checked) {
                    // Check if we should show a rationale
                    val showRationale = activity?.let {
                        ActivityCompat.shouldShowRequestPermissionRationale(
                            it,
                            Manifest.permission.POST_NOTIFICATIONS
                        )
                    } ?: false

                    if (showRationale) {
                        // User denied before but can ask again
                        Toast.makeText(
                            context,
                            "Please allow notifications to get daily trending updates",
                            Toast.LENGTH_LONG
                        ).show()
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    } else {
                        // Permission denied permanently or first time
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                } else {
                    // Already granted or user turned off toggle
                    onToggle(checked)
                }
            } else {
                // Android < 13: no runtime permission
                val notificationsEnabled =
                    NotificationManagerCompat.from(context).areNotificationsEnabled()

                if (!notificationsEnabled) {
                    // Notifications disabled manually, redirect to settings
                    Toast.makeText(
                        context,
                        "Please enable notifications in app settings",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                        putExtra("app_package", context.packageName)
                        putExtra("app_uid", context.applicationInfo.uid)
                    }
                    context.startActivity(intent)
                    onToggle(false)
                } else {
                    onToggle(checked)
                }
            }
        }
    )
}