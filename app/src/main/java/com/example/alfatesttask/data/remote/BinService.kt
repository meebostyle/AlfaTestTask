package com.example.alfatesttask.data.remote

import com.example.alfatesttask.data.remote.model.BinResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BinService {
    @GET("{number}")
    suspend fun getBinInfo(
        @Path("number") number: String
    ): BinResponse

}