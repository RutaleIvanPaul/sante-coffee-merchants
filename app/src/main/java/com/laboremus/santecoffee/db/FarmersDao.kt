package com.laboremus.santecoffee.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FarmersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFarmer(farmer: Farmer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFarmersList(farmers: List<Farmer>): LongArray

    @Query("DELETE FROM farmers_table")
    suspend fun deleteAll()

    @Update
    suspend fun updateFarmer(farmer: Farmer)

    @Query(value = "SELECT * FROM farmers_table ORDER BY timestamp DESC")
    fun getAllFarmersSortedByDate(): LiveData<List<Farmer>>




}
