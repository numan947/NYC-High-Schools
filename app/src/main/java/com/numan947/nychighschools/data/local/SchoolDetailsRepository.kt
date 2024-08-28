package com.numan947.nychighschools.data.local

import javax.inject.Inject
import javax.inject.Singleton

class SchoolDetailsRepository @Inject constructor(private val schoolDetailsDao: SchoolDetailsDao): SchoolDetailsRepositoryInterface {
    override suspend fun insertSchoolDetails(schoolDetailsEntity: SchoolDetailsEntity) {
        schoolDetailsDao.insertSchoolDetails(schoolDetailsEntity)
    }

    override suspend fun deleteSchoolDetails(schoolDetailsEntity: SchoolDetailsEntity) {
        schoolDetailsDao.deleteSchoolDetails(schoolDetailsEntity)
    }

    override suspend fun getSchoolDetails(dbn: String): SchoolDetailsEntity? {
        return schoolDetailsDao.getSchoolDetails(dbn)
    }

    override suspend fun getSavedSchools(): List<String> {
        return schoolDetailsDao.getSavedSchools()
    }

    override suspend fun getAllSchoolDetails(): List<SchoolDetailsEntity> {
        return schoolDetailsDao.getAllSchoolDetails()
    }
}