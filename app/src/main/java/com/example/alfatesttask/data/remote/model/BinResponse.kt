package com.example.alfatesttask.data.remote.model

data class BinResponse(
    val scheme: String?,
    val country: Country,
    val bank: Bank,
)
data class Country(
    val name: String?,
    val latitude: Int?,
    val longitude: Int?,
)

data class Bank (
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)