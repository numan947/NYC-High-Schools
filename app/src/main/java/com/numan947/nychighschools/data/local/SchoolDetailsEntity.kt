package com.numan947.nychighschools.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.numan947.nychighschools.domain.SchoolDetailsModel

@Entity(tableName = "school_details")
data class SchoolDetailsEntity(
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
    companion object{
        fun fromSchoolDetailsModel(schoolDetailsModel: SchoolDetailsModel): SchoolDetailsEntity {
            return SchoolDetailsEntity(
                dbn = schoolDetailsModel.dbn,
                name = schoolDetailsModel.name,
                address = schoolDetailsModel.address,
                email = schoolDetailsModel.email,
                website = schoolDetailsModel.website,
                phone = schoolDetailsModel.phone,
                latitude = schoolDetailsModel.latitude,
                longitude = schoolDetailsModel.longitude,
                overview = schoolDetailsModel.overview,
                opportunities1 = schoolDetailsModel.opportunities1,
                opportunities2 = schoolDetailsModel.opportunities2,
                totalStudents = schoolDetailsModel.totalStudents,
                graduationRate = schoolDetailsModel.graduationRate,
                attendanceRate = schoolDetailsModel.attendanceRate,
                collegeCareerRate = schoolDetailsModel.collegeCareerRate,
                startTime = schoolDetailsModel.startTime,
                endTime = schoolDetailsModel.endTime,
                satTestTakers = schoolDetailsModel.satTestTakers,
                satCriticalReadingAvgScore = schoolDetailsModel.satCriticalReadingAvgScore,
                satMathAvgScore = schoolDetailsModel.satMathAvgScore,
                satWritingAvgScore = schoolDetailsModel.satWritingAvgScore
            )
        }
    }
}
