package com.alwin.eventflow.data.model

import java.time.LocalDate
import java.time.LocalTime

enum class EventCategory(val displayName: String, val colorHex: Long) {
    ACADEMIC("Study & Academic", 0xFF3F51B5),
    EXAM("Exam & Deadlines", 0xFFE53935),
    WORK("Work & Projects", 0xFF00897B),
    PERSONAL("Personal Life", 0xFFFB8C00),
    SOCIAL("Social & Gatherings", 0xFF8E24AA),
    OTHER("General", 0xFF546E7A)
}

enum class Priority(val label: String, val colorHex: Long) {
    LOW("Low", 0xFF43A047),
    MEDIUM("Medium", 0xFFFB8C00),
    HIGH("High", 0xFFE53935)
}

data class Event(
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
    val durationMinutes: Long
        get() {
            val startMinutes = startTime.hour * 60 + startTime.minute
            val endMinutes = endTime.hour * 60 + endTime.minute
            return (endMinutes - startMinutes).toLong().coerceAtLeast(0)
        }
}
