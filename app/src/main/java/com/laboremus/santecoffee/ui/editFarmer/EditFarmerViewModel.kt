package com.laboremus.santecoffee.ui.editFarmer

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laboremus.santecoffee.db.Farmer
import com.laboremus.santecoffee.repository.MainRepository
import kotlinx.coroutines.launch

class EditFarmerViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    fun updateFarmer(farmer: Farmer) = viewModelScope.launch {
        mainRepository.updateFarmer(farmer)
    }
}