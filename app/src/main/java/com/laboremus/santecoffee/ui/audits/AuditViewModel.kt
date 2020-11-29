package com.laboremus.santecoffee.ui.audits

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laboremus.santecoffee.db.Audit
import com.laboremus.santecoffee.repository.MainRepository
import kotlinx.coroutines.launch

class AuditViewModel @ViewModelInject constructor(
        private val mainRepository: MainRepository
) : ViewModel() {

    fun insertAudit(audit: Audit) = viewModelScope.launch {
        mainRepository.insertAudits(audit)
    }

    fun getAudits() = mainRepository.getAllAudits()


    fun getOneFarmer(id:Int) = mainRepository.getOneFarmer(id)

    fun getFarmersAndAudits() = mainRepository.getFarmersAndAudits()


}