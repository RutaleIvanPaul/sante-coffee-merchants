package com.laboremus.santecoffee.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "audit_edits_table")
data class Audit(
        var updatedBy: String,
        var timestamp: Long = 0L
) {
    @PrimaryKey(autoGenerate = true)
    var Id = 0

    @ForeignKey(entity = Farmer::class, parentColumns = arrayOf("Id"),
            childColumns = arrayOf("farmer_Id"), onDelete = ForeignKey.CASCADE)
    var farmer_Id =0
}