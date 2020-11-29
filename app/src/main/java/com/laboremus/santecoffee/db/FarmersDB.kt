package com.laboremus.santecoffee.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Farmer::class, Audit::class],
    version = 1
)

abstract class FarmersDB: RoomDatabase() {
    abstract fun getFarmersDao():FarmersDao
    abstract fun getAuditDao():AuditDao
}