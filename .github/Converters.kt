package com.alwin.eventflow.data.local

import androidx.room.TypeConverter
import com.alwin.eventflow.data.model.EventCategory
import com.alwin.eventflow.data.model.Priority
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Converters {
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    private val timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? = date?.format(dateFormatter)

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it, dateFormatter) }

    @TypeConverter
    fun fromLocalTime(time: LocalTime?): String? = time?.format(timeFormatter)

    @TypeConverter
    fun toLocalTime(value: String?): LocalTime? = value?.let { LocalTime.parse(it, timeFormatter) }

    @TypeConverter
    fun fromEventCategory(category: EventCategory?): String? = category?.name

    @TypeConverter
    fun toEventCategory(value: String?): EventCategory? =
        value?.let { runCatching { EventCategory.valueOf(it) }.getOrDefault(EventCategory.OTHER) }

    @TypeConverter
    fun fromPriority(priority: Priority?): String? = priority?.name

    @TypeConverter
    fun toPriority(value: String?): Priority? =
        value?.let { runCatching { Priority.valueOf(it) }.getOrDefault(Priority.MEDIUM) }
}
