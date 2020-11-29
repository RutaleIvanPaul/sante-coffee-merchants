package com.laboremus.santecoffee.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AuditDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAudits(audit: Audit)

    @Query(value = "SELECT * FROM audit_edits_table ORDER BY timestamp DESC")
    fun getAllAuditsSortedByDate(): LiveData<List<Audit>>

    @Transaction
    @Query("SELECT * FROM farmers_table")
    fun getFarmersandAudits():LiveData<List<FarmerWithAudits>>
}