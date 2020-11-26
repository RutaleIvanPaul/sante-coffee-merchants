package com.example.santecoffee.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Farmer::class],
    version = 1
)

abstract class FarmersDB: RoomDatabase() {
    abstract fun getDao():FarmersDao
}