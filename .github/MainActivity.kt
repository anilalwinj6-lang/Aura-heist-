package com.alwin.eventflow

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.alwin.eventflow.data.model.Event
import com.alwin.eventflow.data.model.EventCategory
import com.alwin.eventflow.data.model.Priority
import com.alwin.eventflow.ui.screens.HomeScreen
import com.alwin.eventflow.ui.theme.EventFlowTheme
import com.alwin.eventflow.ui.viewmodel.EventViewModel
import com.alwin.eventflow.ui.viewmodel.EventViewModelFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class MainActivity : ComponentActivity() {

    private val viewModel: EventViewModel by viewModels {
        val app = application as EventFlowApplication
        EventViewModelFactory(app.repository)
    }

    private val requestNotificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            // Notification permission granted or denied
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        checkAndRequestNotificationPermission()
        populateSampleEventsIfEmpty()

        setContent {
            EventFlowTheme {
                HomeScreen(viewModel = viewModel)
            }
        }
    }

    private fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permissionStatus = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            )
            if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
                requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun populateSampleEventsIfEmpty() {
        val app = application as EventFlowApplication
        lifecycleScope.launch {
            val existingEvents = app.repository.getAllEvents().first()
            if (existingEvents.isEmpty()) {
                val today = LocalDate.now()
                val starterEvents = listOf(
                    Event(
                        title = "Engineering Entrance Exam Registration",
                        description = "Review application portal credentials and submit final verification documents",
                        date = today,
                        startTime = LocalTime.of(10, 0),
                        endTime = LocalTime.of(11, 30),
                        category = EventCategory.EXAM,
                        priority = Priority.HIGH,
                        location = "Admissions Portal",
                        hasReminder = true,
                        reminderMinutesBefore = 30
                    ),
                    Event(
                        title = "Machine Learning & Neural Nets Lecture",
                        description = "Backpropagation architectures and gradient descent optimization study session",
                        date = today,
                        startTime = LocalTime.of(14, 0),
                        endTime = LocalTime.of(15, 30),
                        category = EventCategory.ACADEMIC,
                        priority = Priority.MEDIUM,
                        location = "Lecture Hall / Online",
                        hasReminder = true,
                        reminderMinutesBefore = 15
                    ),
                    Event(
                        title = "Project Sprint Review",
                        description = "Demo new Android UI features and discuss Room DB architecture",
                        date = today.plusDays(1),
                        startTime = LocalTime.of(16, 0),
                        endTime = LocalTime.of(17, 0),
                        category = EventCategory.WORK,
                        priority = Priority.MEDIUM,
                        location = "Google Meet",
                        hasReminder = true,
                        reminderMinutesBefore = 15
                    ),
                    Event(
                        title = "Workout & Evening Walk",
                        description = "Daily fitness session and outdoor recharge",
                        date = today,
                        startTime = LocalTime.of(18, 30),
                        endTime = LocalTime.of(19, 30),
                        category = EventCategory.PERSONAL,
                        priority = Priority.LOW,
                        location = "Campus / Park",
                        hasReminder = false
                    )
                )

                starterEvents.forEach { event ->
                    app.repository.insertEvent(event)
                }
            }
        }
    }
}
