package com.example.notificationprac

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChanal: NotificationChannel
    private lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notification"
    private val description = "Test Notification"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btn)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        button.setOnClickListener {
            val intent = Intent(this, NotificationViewActivity::class.java)
            val pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            val contenView = RemoteViews(packageName, R.layout.activity_notification_view)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChanal =
                    NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChanal.enableLights(true)
                notificationChanal.lightColor = Color.GREEN
                notificationChanal.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChanal)
                builder = Notification.Builder(this, channelId)
                    .setContent(contenView)

                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            this.resources,
                            R.drawable.ic_launcher_background
                        )
                    )
                    .setContentIntent(pendingIntent)
            } else {
                builder = Notification.Builder(this)
                    .setContent(contenView)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            this.resources,
                            R.drawable.ic_launcher_background
                        )
                    )
                    .setContentIntent(pendingIntent)

            }
            notificationManager.notify(1234, builder.build())
        }

    }
}