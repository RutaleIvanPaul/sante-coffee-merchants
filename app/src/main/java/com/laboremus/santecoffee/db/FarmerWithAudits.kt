package com.laboremus.santecoffee.db

import androidx.room.Embedded
import androidx.room.Relation

data class FarmerWithAudits(
        @Embedded val farmer: Farmer,
        @Relation(
                parentColumn = "Id",
                entityColumn = "farmer_Id"
        )
        val audits:List<Audit>
) {
}