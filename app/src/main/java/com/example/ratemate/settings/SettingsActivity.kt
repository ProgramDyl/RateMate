package com.example.ratemate.ui.screens

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat




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
                        switch1State = isEnabled
                        if (isEnabled) {
                            // Trigger a notification
                            val notificationManager =
                                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                            val notification = NotificationCompat.Builder(context, "rate_mate_notifications")
                                .setSmallIcon(android.R.drawable.ic_dialog_info)
                                .setContentTitle("Notifications Enabled")
                                .setContentText("You have enabled notifications for RateMate!")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .build()
                            notificationManager.notify(1, notification)
                        }
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
