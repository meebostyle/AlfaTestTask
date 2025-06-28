package com.example.alfatesttask.data.room.history.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "history")
data class HistoryDbo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bin: String,
    val type: String,
    val country: String,
    val latitude: String,
    val longitude: String,
    val coordinates: String,
    val bank: String,
    val phone: String,
    val city: String,
    val bankUrl: String,
    val dateTime: LocalDateTime
)