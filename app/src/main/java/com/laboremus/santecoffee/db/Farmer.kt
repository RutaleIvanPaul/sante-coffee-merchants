package com.laboremus.santecoffee.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "farmers_table")
data class Farmer (
    var name:String = "",
    var gender: String = "",
    var birthCertificateUrl: String = "",
    var nationalIdnumber: String = "",
    var phoneNumber: String = "",
    var timestamp:Long = 0L,
    var lastUpdatedBy: String = ""
): Parcelable{
    @PrimaryKey(autoGenerate = true)
    var Id = 0
}
