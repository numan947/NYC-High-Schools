package com.numan947.nychighschools.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.numan947.nychighschools.data.local.SchoolDetailsEntity


data class SchoolDetailsModel(
    // whether saved or not
    var isSaved: Boolean = false,
    // school's contact information
    var dbn: String,
    var name: String,
    var address: String,
    var email: String?,
    var website: String?,
    var phone: String?,
    var latitude: Double?,
    var longitude: Double?,

    // school's academic information
    var overview: String?,
    var opportunities1: String?,
    var opportunities2: String?,

    // school's student body and performance
    var totalStudents: Int?,
    var graduationRate: Double?,
    var attendanceRate: Double?,
    var collegeCareerRate: Double?,
    var startTime: String?,
    var endTime: String?,

    // school's SAT scores
    var satTestTakers: Int?,
    var satCriticalReadingAvgScore: Double?,
    var satMathAvgScore: Double?,
    var satWritingAvgScore: Double?
){
    companion object{
        fun fromSchoolDetailsEntity(schoolDetailsEntity: SchoolDetailsEntity): SchoolDetailsModel {
            return SchoolDetailsModel(
                isSaved = true,
                dbn = schoolDetailsEntity.dbn,
                name = schoolDetailsEntity.name,
                address = schoolDetailsEntity.address,
                email = schoolDetailsEntity.email,
                website = schoolDetailsEntity.website,
                phone = schoolDetailsEntity.phone,
                latitude = schoolDetailsEntity.latitude,
                longitude = schoolDetailsEntity.longitude,
                overview = schoolDetailsEntity.overview,
                opportunities1 = schoolDetailsEntity.opportunities1,
                opportunities2 = schoolDetailsEntity.opportunities2,
                totalStudents = schoolDetailsEntity.totalStudents,
                graduationRate = schoolDetailsEntity.graduationRate,
                attendanceRate = schoolDetailsEntity.attendanceRate,
                collegeCareerRate = schoolDetailsEntity.collegeCareerRate,
                startTime = schoolDetailsEntity.startTime,
                endTime = schoolDetailsEntity.endTime,
                satTestTakers = schoolDetailsEntity.satTestTakers,
                satCriticalReadingAvgScore = schoolDetailsEntity.satCriticalReadingAvgScore,
                satMathAvgScore = schoolDetailsEntity.satMathAvgScore,
                satWritingAvgScore = schoolDetailsEntity.satWritingAvgScore
            )
        }
    }
}