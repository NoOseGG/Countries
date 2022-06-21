package com.example.countries.koin

import com.example.countries.viewmodel.ListCountryViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ListCountryViewModel)
}