package com.example.santecoffee.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FarmersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFarmer(farmer: Farmer)

    @Query(value = "SELECT * FROM farmers_table ORDER BY timestamp DESC")
    fun getAllFarmersSortedByDate(): LiveData<List<Farmer>>


}
