package com.numan947.nychighschools.ui.schoollist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numan947.nychighschools.data.local.SchoolDetailsRepository
import com.numan947.nychighschools.data.network.ApiService
import com.numan947.nychighschools.domain.HighSchoolList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SchoolListViewModel @Inject constructor(
    private val apiService: ApiService,
    private val schoolDetailsRepository: SchoolDetailsRepository
) : ViewModel() {
    private var isLoading = MutableLiveData<Boolean>()
    private val _hsMutableList = MutableLiveData<HighSchoolList?>()
    val highSchoolListLiveData: LiveData<HighSchoolList?> get() = _hsMutableList
    private var _savedSchoolsDbn: List<String> = emptyList()
    val savedSchoolsDbn: List<String> get() = _savedSchoolsDbn
    private var fetchedData: HighSchoolList? = null


    fun getHighSchools() {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                // fetch saved schools from local db
                _savedSchoolsDbn = schoolDetailsRepository.getSavedSchools()
                if (fetchedData == null) {
                    val response = apiService.getAllHighSchools() // avoid fetching data again if already fetched
                    fetchedData = if (response.isSuccessful) {
                        response.body()
                    } else {
                        null
                    }
                }
                fetchedData?.let {
                    it.forEach { school ->
                        if (_savedSchoolsDbn.contains(school.dbn)) {
                            school.isSaved = true
                        }
                    }
                }
                _hsMutableList.postValue(fetchedData)
                isLoading.postValue(false)
            } catch (e: Exception) {
                e.printStackTrace()
                isLoading.postValue(false)
                _hsMutableList.postValue(null)
            }
        }
    }
}