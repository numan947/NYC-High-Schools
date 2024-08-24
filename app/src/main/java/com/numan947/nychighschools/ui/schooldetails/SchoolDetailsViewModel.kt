package com.numan947.nychighschools.ui.schooldetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numan947.nychighschools.data.network.ApiService
import com.numan947.nychighschools.domain.HighSchoolListItem
import com.numan947.nychighschools.domain.HighSchoolScores
import com.numan947.nychighschools.domain.SchoolDetailsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SchoolDetailsViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {
    private var isLoading = MutableLiveData<Boolean>()
    private var highSchoolListItem: HighSchoolListItem? = null
    private var schoolDetails: MutableLiveData<SchoolDetailsModel> = MutableLiveData()
    val data: MutableLiveData<SchoolDetailsModel> get() = schoolDetails

    fun setSchool(school: HighSchoolListItem) {
        highSchoolListItem = school
        schoolDetails.value = SchoolDetailsModel(
            dbn = school.dbn,
            name = school.school_name,
            address = buildString {
                school.primary_address_line_1
                school.city
                school.state_code
                school.zip
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

    fun getHighSchoolScores() {
        if (highSchoolListItem == null) return
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = apiService.getAllSATScores(highSchoolListItem!!.dbn)
            if (response.isSuccessful) {
                val scores = response.body()!!
                if (scores.isEmpty()) { //TODO: handle when no scores are available
                    isLoading.postValue(false)
                    return@launch
                }
                schoolDetails.postValue(
                    schoolDetails.value!!.copy(
                        satTestTakers = scores[0].numOfSatTestTakers.toIntOrNull(),
                        satCriticalReadingAvgScore = scores[0].satCriticalReadingAvgScore.toDoubleOrNull(),
                        satMathAvgScore = scores[0].satMathAvgScore.toDoubleOrNull(),
                        satWritingAvgScore = scores[0].satWritingAvgScore.toDoubleOrNull()
                    )
                )
                println("SAT Scores: $scores")
            }
            isLoading.postValue(false)
        }
    }


}