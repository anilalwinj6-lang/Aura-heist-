package com.alwin.eventflow.data.repository

import com.alwin.eventflow.data.local.EventDao
import com.alwin.eventflow.data.local.EventEntity
import com.alwin.eventflow.data.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalTime

class EventRepository(private val eventDao: EventDao) {

    fun getAllEvents(): Flow<List<Event>> =
        eventDao.getAllEvents().map { entities -> entities.map { it.toDomainModel() } }

    fun getEventsByDate(date: LocalDate): Flow<List<Event>> =
        eventDao.getEventsByDate(date).map { entities -> entities.map { it.toDomainModel() } }

    fun getUpcomingEvents(date: LocalDate, time: LocalTime): Flow<List<Event>> =
        eventDao.getUpcomingEvents(date, time).map { entities -> entities.map { it.toDomainModel() } }

    fun getEventById(id: Long): Flow<Event?> =
        eventDao.getEventById(id).map { it?.toDomainModel() }

    fun searchEvents(query: String): Flow<List<Event>> =
        eventDao.searchEvents(query).map { entities -> entities.map { it.toDomainModel() } }

    suspend fun insertEvent(event: Event): Long =
        eventDao.insertEvent(EventEntity.fromDomainModel(event))

    suspend fun updateEvent(event: Event) =
        eventDao.updateEvent(EventEntity.fromDomainModel(event))

    suspend fun deleteEvent(event: Event) =
        eventDao.deleteEvent(EventEntity.fromDomainModel(event))

    suspend fun deleteEventById(id: Long) =
        eventDao.deleteEventById(id)

    suspend fun toggleEventCompletion(id: Long, isCompleted: Boolean) =
        eventDao.updateEventCompletion(id, isCompleted)
}
