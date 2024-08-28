package com.numan947.nychighschools.data.local

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "school_details")
data class SchoolDetailsEntity(
    @Ignore
    var isSaved: Boolean = false,
    // school's contact information
    @PrimaryKey(autoGenerate = false)
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
    // empty constructor for room
    constructor(): this(
        dbn = "",
        name = "",
        address = "",
        email = "",
        website = "",
        phone = "",
        latitude = 0.0,
        longitude = 0.0,
        overview = "",
        opportunities1 = "",
        opportunities2 = "",
        totalStudents = 0,
        graduationRate = 0.0,
        attendanceRate = 0.0,
        collegeCareerRate = 0.0,
        startTime = "",
        endTime = "",
        satTestTakers = 0,
        satCriticalReadingAvgScore = 0.0,
        satMathAvgScore = 0.0,
        satWritingAvgScore = 0.0
    )
}