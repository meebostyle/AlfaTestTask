package com.example.alfatesttask.domain.model

import com.example.alfatesttask.data.remote.model.BinResponse
import com.example.alfatesttask.data.room.history.dbo.HistoryDbo
import java.time.LocalDateTime

data class BinInfoModel (
    val bin: String = "-",
    val type: String= "-",
    val country: String= "-",
    val latitude: String= "-",
    val longitude: String= "-",
    val coordinates: String= "-",
    val bank: String= "-",
    val phone: String= "-",
    val city: String= "-",
    val bankUrl: String= "-",
    val dateTime: LocalDateTime? = null
)

fun BinResponse.toBinInfoModel(number: String): BinInfoModel{
    return BinInfoModel(
        bin = number,
        type = scheme ?:"-",
        country = country.name ?:"-",
        longitude = country.longitude.toString() ,
        latitude = country.latitude.toString(),
        coordinates = "${country.latitude}/${country.longitude}",
        bank = bank.name ?:"-",
        phone = bank.phone ?:"-",
        city = bank.city ?:"-",
        bankUrl = bank.url ?:"-",

    )
}

fun BinInfoModel.toHistoryDbo(): HistoryDbo{
    return HistoryDbo(
        id = 0,
        bin = bin,
        type = type,
        country = country,
        latitude = latitude,
        longitude = longitude,
        coordinates = coordinates,
        bank = bank,
        phone = phone,
        city = city,
        bankUrl = bankUrl,
        dateTime = LocalDateTime.now()
    )
}

fun HistoryDbo.toBinInfoModel(): BinInfoModel{
    return BinInfoModel(
        bin = bin,
        type = type,
        country = country,
        longitude = longitude,
        latitude = latitude,
        coordinates = coordinates,
        bank = bank,
        phone = phone,
        city = city,
        bankUrl = bankUrl,
        dateTime = dateTime
    )
}
