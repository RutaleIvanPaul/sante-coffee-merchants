package com.laboremus.santecoffee.ui.addFarmer

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laboremus.santecoffee.db.Farmer
import com.laboremus.santecoffee.repository.MainRepository
import kotlinx.coroutines.launch

class AddFarmerViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun insertFarmer(farmer: Farmer) = viewModelScope.launch {
        mainRepository.insertFarmer(farmer)
    }

}