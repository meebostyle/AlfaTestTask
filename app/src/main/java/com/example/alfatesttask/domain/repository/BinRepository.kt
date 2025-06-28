package com.example.alfatesttask.domain.repository

import com.example.alfatesttask.data.remote.BinService
import com.example.alfatesttask.data.room.history.HistoryDatabase
import com.example.alfatesttask.domain.model.BinInfoModel
import com.example.alfatesttask.domain.model.toBinInfoModel
import com.example.alfatesttask.domain.model.toHistoryDbo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BinRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://lookup.binlist.net")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(BinService::class.java)

    suspend fun getBinInfo(number: String): BinInfoModel {
        val result = service.getBinInfo(number).toBinInfoModel(number)
        if (result.bin != "-")
            saveSearch(result)
        return result
    }

    suspend fun getHistory():List<BinInfoModel>{
        val database = HistoryDatabase.db
        return database.historyDao().getAll().map{ it.toBinInfoModel() }
    }

    private suspend fun saveSearch(binInfo: BinInfoModel) {
        val database = HistoryDatabase.db
        database.historyDao().insertAll(binInfo.toHistoryDbo())
    }


}