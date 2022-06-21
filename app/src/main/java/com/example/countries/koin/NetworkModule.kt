package com.example.countries.koin

import com.example.countries.retrofit.CountryApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://restcountries.com/v3.1/"

val networkModule = module {

    single<CountryApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(CountryApi::class.java)
    }

    single {
        OkHttpClient.Builder().build()
    }

}