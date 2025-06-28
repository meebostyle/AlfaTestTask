package com.example.alfatesttask.data.room.history

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.alfatesttask.data.room.converter.Converters
import com.example.alfatesttask.data.room.history.dao.HistoryDao
import com.example.alfatesttask.data.room.history.dbo.HistoryDbo


@Database(entities = [HistoryDbo::class], version = 1)
@TypeConverters(Converters::class)
abstract class HistoryDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object{
        private var _db: HistoryDatabase? = null
        val db get() = _db!!

        fun create(context: Context){
            if (_db == null)
                _db = Room.databaseBuilder(context, HistoryDatabase::class.java, "history.db").build()
        }
    }
}