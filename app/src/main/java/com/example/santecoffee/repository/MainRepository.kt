package com.example.santecoffee.repository

import com.example.santecoffee.db.Farmer
import com.example.santecoffee.db.FarmersDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    val farmersDao: FarmersDao
) {
    suspend fun insertFarmer(farmer: Farmer) = farmersDao.insertFarmer(farmer)

    fun getAllFarmers() = farmersDao.getAllFarmersSortedByDate()
}