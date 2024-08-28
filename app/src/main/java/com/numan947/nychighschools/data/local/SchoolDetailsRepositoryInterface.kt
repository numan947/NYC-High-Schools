package com.numan947.nychighschools.data.local

import javax.inject.Singleton

@Singleton
interface SchoolDetailsRepositoryInterface {
    suspend fun insertSchoolDetails(schoolDetailsEntity: SchoolDetailsEntity)
    suspend fun deleteSchoolDetails(schoolDetailsEntity: SchoolDetailsEntity)
    suspend fun getSchoolDetails(dbn: String): SchoolDetailsEntity?
    suspend fun getSavedSchools(): List<String>
    suspend fun getAllSchoolDetails(): List<SchoolDetailsEntity>
}