package com.numan947.nychighschools.domain


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.numan947.nychighschools.data.local.SchoolDetailsEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class HighSchoolListItem(
    var isSaved:Boolean = false,
    // school identification and location
    @SerializedName("dbn")
    val dbn: String,
    @SerializedName("school_name")
    val school_name: String,
    @SerializedName("primary_address_line_1")
    val primary_address_line_1: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("zip")
    val zip: String,
    @SerializedName("state_code")
    val state_code: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,

    // contact information
    @SerializedName("phone_number")
    val phone_number: String?,
    @SerializedName("school_email")
    val school_email: String?,
    @SerializedName("website")
    val website: String?,

    // academic information
    @SerializedName("overview_paragraph")
    val overview_paragraph: String?,
    @SerializedName("academicopportunities1")
    val academicopportunities1: String?,
    @SerializedName("academicopportunities2")
    val academicopportunities2: String?,

    // Student body and performance
    @SerializedName("total_students")
    val total_students: Int?,
    @SerializedName("graduation_rate")
    val graduation_rate: Double?,
    @SerializedName("attendance_rate")
    val attendance_rate: Double?,
    @SerializedName("college_career_rate")
    val college_career_rate: Double?,

    // others
    @SerializedName("start_time")
    val start_time: String?,
    @SerializedName("end_time")
    val end_time: String?,
) : Parcelable
{
    companion object {
        fun fromSchoolDetailsEntity(schoolDetailsEntity: SchoolDetailsEntity): HighSchoolListItem {
            val addresslines = schoolDetailsEntity.address.split(", ")
            print(schoolDetailsEntity.address)
            println("addresslines: $addresslines")

            return HighSchoolListItem(
                isSaved = true,
                dbn = schoolDetailsEntity.dbn,
                school_name = schoolDetailsEntity.name,
                primary_address_line_1 = addresslines[0],
                city = addresslines[1],
                zip = addresslines[3],
                state_code = addresslines[2],
                latitude = schoolDetailsEntity.latitude?:0.0,
                longitude = schoolDetailsEntity.longitude?:0.0,
                phone_number = schoolDetailsEntity.phone,
                school_email = schoolDetailsEntity.email,
                website = schoolDetailsEntity.website,
                overview_paragraph = schoolDetailsEntity.overview,
                academicopportunities1 = "N/A",
                academicopportunities2 = "N/A",
                total_students = schoolDetailsEntity.totalStudents,
                graduation_rate = schoolDetailsEntity.graduationRate,
                attendance_rate = schoolDetailsEntity.attendanceRate,
                college_career_rate = schoolDetailsEntity.collegeCareerRate,
                start_time = schoolDetailsEntity.startTime,
                end_time = schoolDetailsEntity.endTime
            )
        }
    }
}