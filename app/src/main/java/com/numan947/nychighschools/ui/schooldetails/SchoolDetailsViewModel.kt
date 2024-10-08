package com.numan947.nychighschools.ui.schooldetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numan947.nychighschools.data.local.SchoolDetailsEntity
import com.numan947.nychighschools.data.local.SchoolDetailsRepositoryInterface
import com.numan947.nychighschools.data.network.ApiService
import com.numan947.nychighschools.domain.HighSchoolListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolDetailsViewModel @Inject constructor(
    private val apiService: ApiService,
    private val schoolDetailsRepository: SchoolDetailsRepositoryInterface
) : ViewModel() {
    private var isLoading = MutableLiveData<Boolean>()
    private var saveState = MutableLiveData<Boolean>()
    private var highSchoolListItem: HighSchoolListItem? = null
    private var schoolDetails: MutableLiveData<SchoolDetailsEntity> = MutableLiveData()

    val saveStateData: MutableLiveData<Boolean> get() = saveState
    val data: MutableLiveData<SchoolDetailsEntity> get() = schoolDetails
    val loading: MutableLiveData<Boolean> get() = isLoading

    fun setSchool(school: HighSchoolListItem) {
        highSchoolListItem = school
        schoolDetails.value = SchoolDetailsEntity.fromSchoolListItem(school)
        saveState.value = school.isSaved
    }

    fun getHighSchoolScores() {
        if (highSchoolListItem == null) return
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                // check if the school is already saved, if yes then fetch data from local db
                if (highSchoolListItem!!.isSaved) {
//                    println("Fetching from local db")
                    val details = schoolDetailsRepository.getSchoolDetails(highSchoolListItem!!.dbn)
                    if (details != null) {
                        schoolDetails.postValue(details.copy(isSaved = true))
                        isLoading.postValue(false)
                        return@launch
                    }
                }

//                println("Fetching from network")
                val response = apiService.getAllSATScores(highSchoolListItem!!.dbn)
                if (response.isSuccessful) {
                    val scores = response.body()!!
                    if (scores.isEmpty()) {
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
                    isLoading.postValue(false)
                }
            }
            catch (e: Exception) {
                isLoading.postValue(false)
                return@launch
            }
        }
    }

    fun saveOrDeleteSchool() {
        if (highSchoolListItem == null) return
        viewModelScope.launch {
            val school = schoolDetails.value!!
            Log.v( "SchoolDetails", "School is saved: --> ${school.isSaved}")
            if (school.isSaved) {
                schoolDetailsRepository.deleteSchoolDetails(school)
                saveState.postValue(false)
            } else {
                schoolDetailsRepository.insertSchoolDetails(school)
                saveState.postValue(true)
            }
            schoolDetails.postValue(school.copy(isSaved = !school.isSaved))
        }
    }
}