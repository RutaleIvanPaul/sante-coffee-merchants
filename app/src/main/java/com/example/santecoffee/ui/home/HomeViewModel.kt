package com.example.santecoffee.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.santecoffee.repository.MainRepository

class HomeViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

    val farmers_list = mainRepository.getAllFarmers()

}