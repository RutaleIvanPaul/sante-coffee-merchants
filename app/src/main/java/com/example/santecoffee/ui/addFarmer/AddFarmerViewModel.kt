package com.example.santecoffee.ui.addFarmer

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.santecoffee.db.Farmer
import com.example.santecoffee.repository.MainRepository
import kotlinx.coroutines.launch

class AddFarmerViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

    fun insertFarmer(farmer: Farmer) = viewModelScope.launch {
        mainRepository.insertFarmer(farmer)
    }

}