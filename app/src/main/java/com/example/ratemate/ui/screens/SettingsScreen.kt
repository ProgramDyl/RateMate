package com.example.ratemate.ui.screens

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ButtonDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Switch
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

@Composable
fun SettingsScreen() {
    // State variables for the switches
    var switch1State by remember { mutableStateOf(false) } // Enable Notifications
    var darkModeState by remember { mutableStateOf(false) } // Dark Mode

    // Dynamic colors based on Dark Mode state
    val backgroundColor = if (darkModeState) Color(0xFF263238) else Color(0xFFE0F7FA) // Dark vs Light
    val textColor = if (darkModeState) Color.White else Color.Black

    // Local context for notifications
    val context = LocalContext.current

    // Permission state for notifications
    var hasNotificationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    // Launcher for requesting the permission
    val requestPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            hasNotificationPermission = isGranted
        }

    // Create a notification channel (required for Android 8+)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            "rate_mate_notifications",
            "RateMate Notifications",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Channel for RateMate notifications"
        }
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    // UI Layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor), // Dynamically set the background color
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // First Switch: Enable Notifications
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Enable Notifications",
                    color = textColor // Dynamically set text color
                )
                Switch(
                    checked = switch1State,
                    onCheckedChange = { isEnabled ->
                        if (isEnabled && !hasNotificationPermission) {
                            // Request permission
                            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        } else if (isEnabled && hasNotificationPermission) {
                            // Show notification
                            val notification = NotificationCompat.Builder(context, "rate_mate_notifications")
                                .setSmallIcon(android.R.drawable.ic_dialog_info)
                                .setContentTitle("Notifications Enabled")
                                .setContentText("You have enabled notifications for RateMate!")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .build()
                            NotificationManagerCompat.from(context).notify(1, notification)
                        }
                        switch1State = isEnabled
                    }
                )
            }

            // Second Switch: Dark Mode
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Dark Mode",
                    color = textColor // Dynamically set text color
                )
                Switch(
                    checked = darkModeState,
                    onCheckedChange = { darkModeState = it } // Update Dark Mode state
                )
            }

            // Reset Button
            Button(
                onClick = {
                    // Reset both switches to their default states
                    switch1State = false
                    darkModeState = false
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red) // Set button color to red
            ) {
                Text(
                    text = "Reset to Default",
                    color = textColor // Dynamically set button text color
                )
            }
        }
    }
}
