package com.example.menu_alta_listadoplanetstarwars.data.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.menu_alta_listadoplanetstarwars.R
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
class NotificationHelper(private val context: Context) {


    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val notificationChannelID = "planet_channel_id"

    init {
        createNotificationChannel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            notificationChannelID,
            "Planetas",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notificaciones de creación de planetas"
        }
        notificationManager.createNotificationChannel(channel)
    }

    // 2. Añadir esta anotación para quitar el error rojo de .notify()
    @SuppressLint("MissingPermission")
    fun showSimpleNotification(contentTitle: String, contentText: String) {
        val notification = NotificationCompat.Builder(context, notificationChannelID)
            .setContentTitle(contentTitle)
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(Random.nextInt(), notification)
    }
}