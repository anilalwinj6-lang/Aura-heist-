package com.alwin.eventflow

import android.app.Application
import com.alwin.eventflow.data.local.EventDatabase
import com.alwin.eventflow.data.repository.EventRepository
import com.alwin.eventflow.notification.NotificationHelper

class EventFlowApplication : Application() {

    val database by lazy { EventDatabase.getDatabase(this) }
    val repository by lazy { EventRepository(database.eventDao()) }

    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannel(this)
    }
}
