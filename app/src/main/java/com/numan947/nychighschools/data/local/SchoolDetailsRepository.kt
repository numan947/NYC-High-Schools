package com.numan947.nychighschools.data.local

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchoolDetailsRepository @Inject constructor(private val schoolDetailsDao: SchoolDetailsDao) {
    suspend fun insertSchoolDetails(schoolDetailsEntity: SchoolDetailsEntity) {
        schoolDetailsDao.insertSchoolDetails(schoolDetailsEntity)
    }

    suspend fun deleteSchoolDetails(schoolDetailsEntity: SchoolDetailsEntity) {
        schoolDetailsDao.deleteSchoolDetails(schoolDetailsEntity)
    }

    suspend fun getSchoolDetails(dbn: String): SchoolDetailsEntity? {
        return schoolDetailsDao.getSchoolDetails(dbn)
    }

    suspend fun getSavedSchools(): List<String> {
        return schoolDetailsDao.getSavedSchools()
    }

    suspend fun getAllSchoolDetails(): List<SchoolDetailsEntity> {
        return schoolDetailsDao.getAllSchoolDetails()
    }
}