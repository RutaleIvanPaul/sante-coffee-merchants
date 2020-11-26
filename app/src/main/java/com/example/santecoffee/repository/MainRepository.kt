package com.example.santecoffee.repository

import com.example.santecoffee.db.Farmer
import com.example.santecoffee.db.FarmersDao
import com.example.santecoffee.services.RequestManager
import javax.inject.Inject

class MainRepository @Inject constructor(
    val farmersDao: FarmersDao,
    val requestManager: RequestManager
) {
    suspend fun insertFarmer(farmer: Farmer) = farmersDao.insertFarmer(farmer)

    fun getAllFarmers() = farmersDao.getAllFarmersSortedByDate()

    fun getFarmersfromApi() = requestManager.returnPostsFromApi()
}