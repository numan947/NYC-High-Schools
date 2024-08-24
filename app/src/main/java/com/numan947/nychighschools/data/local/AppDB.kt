package com.numan947.nychighschools.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SchoolDetailsEntity::class], version = 1)
abstract class AppDB:RoomDatabase() {
    abstract fun schoolDetailsDao(): SchoolDetailsDao
}