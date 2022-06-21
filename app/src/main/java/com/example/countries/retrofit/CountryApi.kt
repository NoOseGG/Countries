package com.example.countries.retrofit

import com.example.countries.model.Country
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {

    @GET("name/{countryName}")
    suspend fun getCountry(
       @Path("countryName") countryName: String
    ) : List<Country>
}