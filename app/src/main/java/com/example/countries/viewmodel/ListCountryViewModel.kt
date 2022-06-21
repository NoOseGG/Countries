package com.example.countries.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.model.Country
import com.example.countries.retrofit.CountryApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ListCountryViewModel(
    private val api: CountryApi
) : ViewModel() {
    val flow = MutableSharedFlow<List<Country>>()






    fun search(nameCountry: String) {
        viewModelScope.launch {
            val country = api.getCountry(nameCountry)
            flow.emit(country)
        }
    }
}