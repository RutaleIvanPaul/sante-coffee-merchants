package com.laboremus.santecoffee.repository

import com.laboremus.santecoffee.db.Farmer
import com.laboremus.santecoffee.db.FarmersDao
import com.laboremus.santecoffee.services.RequestManager
import javax.inject.Inject

class MainRepository @Inject constructor(
    val farmersDao: FarmersDao,
    val requestManager: RequestManager
) {

    suspend fun insertFarmer(farmer: Farmer) = farmersDao.insertFarmer(farmer)

    suspend fun insertFarmers(farmers: List<Farmer>) = farmersDao.insertFarmersList(farmers)

    suspend fun updateFarmer(farmer: Farmer) = farmersDao.updateFarmer(farmer)

    suspend fun deleteAll()  = farmersDao.deleteAll()

    fun getAllFarmers() = farmersDao.getAllFarmersSortedByDate()

    fun getFarmersfromApi() = requestManager.returnPostsFromApi()


}