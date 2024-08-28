package com.numan947.nychighschools.data.local

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.numan947.nychighschools.domain.HighSchoolListItem

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

    companion object{
        fun fromSchoolListItem(school: HighSchoolListItem): SchoolDetailsEntity{
            return SchoolDetailsEntity(
                isSaved = school.isSaved,
                dbn = school.dbn,
                name = school.school_name,
                address = buildString {
                    append(school.primary_address_line_1)
                    append(", ")
                    append(school.city)
                    append(", ")
                    append(school.state_code)
                    append(", ")
                    append(school.zip)
                },
                email = school.school_email,
                website = school.website,
                phone = school.phone_number,
                latitude = school.latitude,
                longitude = school.longitude,
                overview = school.overview_paragraph,
                opportunities1 = school.academicopportunities1,
                opportunities2 = school.academicopportunities2,
                totalStudents = school.total_students,
                graduationRate = school.graduation_rate,
                attendanceRate = school.attendance_rate,
                collegeCareerRate = school.college_career_rate,
                startTime = school.start_time,
                endTime = school.end_time,
                satTestTakers = -1,
                satCriticalReadingAvgScore = -1.0,
                satMathAvgScore = -1.0,
                satWritingAvgScore = -1.0
            )
        }
    }

}