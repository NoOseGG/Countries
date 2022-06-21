package com.example.countries.model

import java.io.Serializable

data class Country(
    val name: Name,
    val population: Int,
    val continents: List<String>,
    val flags: Flags,
    val capital: List<String>,
    val capitalInfo: LatLng
) : Serializable

data class Name(
    val common: String,
    val official: String,
)

data class LatLng(
    val latlng: List<Double>
)

data class Flags (
    val png: String
)


