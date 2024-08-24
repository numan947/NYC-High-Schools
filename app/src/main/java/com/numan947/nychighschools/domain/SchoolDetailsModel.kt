package com.numan947.nychighschools.domain

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
)