package com.numan947.nychighschools.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SchoolDetailsDao {
    @Insert
    suspend fun insertSchoolDetails(schoolDetailsEntity: SchoolDetailsEntity)
    @Delete
    suspend fun deleteSchoolDetails(schoolDetailsEntity: SchoolDetailsEntity)

    @Query("SELECT * FROM school_details WHERE dbn = :dbn")
    suspend fun getSchoolDetails(dbn: String): SchoolDetailsEntity?
    @Query("SELECT dbn FROM school_details")
    suspend fun getSavedSchools(): List<String>

}