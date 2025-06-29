package com.example.alfatesttask

import android.app.Application
import android.content.Context
import com.example.alfatesttask.data.room.history.HistoryDatabase

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        initDatabases(applicationContext)
    }

    private fun initDatabases(context: Context) {
        HistoryDatabase.create(context)
    }
}