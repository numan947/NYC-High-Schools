package com.numan947.nychighschools.domain


import com.google.gson.annotations.SerializedName

data class HighSchoolListItem(
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
)