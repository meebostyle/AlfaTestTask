package com.example.alfatesttask.data.room.history.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.alfatesttask.data.room.history.dbo.HistoryDbo

@Dao
interface HistoryDao {
    @Insert
    suspend fun insertAll(vararg history: HistoryDbo)

    @Query("SELECT * FROM history")
    suspend fun getAll(): List<HistoryDbo>
}