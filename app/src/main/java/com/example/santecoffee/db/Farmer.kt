package com.example.santecoffee.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "farmers_table")
data class Farmer (
    var name:String = "",
    var gender: String = "",
    var birthCertificateUrl: String = "",
    var nationalIdnumber: String = "",
    var phoneNumber: String = "",
    var timestamp:Long = 0L,
    var lastUpdatedBy: String = ""
){
    @PrimaryKey(autoGenerate = true)
    var Id:Int? = null
}
