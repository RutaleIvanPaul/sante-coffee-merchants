package com.laboremus.santecoffee.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.laboremus.santecoffee.db.Farmer
import com.laboremus.santecoffee.repository.MainRepository
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : ViewModel(){

    val farmers_list = mainRepository.getAllFarmers()

    val farmers_from_api = mainRepository.getFarmersfromApi()

    fun clearTable() = viewModelScope.launch {
        mainRepository.deleteAll()
    }

    fun addAllFarmers(farmers: List<Farmer>) = viewModelScope.launch {
        mainRepository.insertFarmers(farmers)
    }

}