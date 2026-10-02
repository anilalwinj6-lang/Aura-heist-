package com.alwin.eventflow.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alwin.eventflow.data.model.Event
import com.alwin.eventflow.data.model.EventCategory
import com.alwin.eventflow.data.model.Priority
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val category: EventCategory = EventCategory.OTHER,
    val priority: Priority = Priority.MEDIUM,
    val location: String = "",
    val hasReminder: Boolean = false,
    val reminderMinutesBefore: Int = 15,
    val isCompleted: Boolean = false
) {
    fun toDomainModel(): Event = Event(
        id = id,
        title = title,
        description = description,
        date = date,
        startTime = startTime,
        endTime = endTime,
        category = category,
        priority = priority,
        location = location,
        hasReminder = hasReminder,
        reminderMinutesBefore = reminderMinutesBefore,
        isCompleted = isCompleted
    )

    companion object {
        fun fromDomainModel(event: Event): EventEntity = EventEntity(
            id = event.id,
            title = event.title,
            description = event.description,
            date = event.date,
            startTime = event.startTime,
            endTime = event.endTime,
            category = event.category,
            priority = event.priority,
            location = event.location,
            hasReminder = event.hasReminder,
            reminderMinutesBefore = event.reminderMinutesBefore,
            isCompleted = event.isCompleted
        )
    }
}
