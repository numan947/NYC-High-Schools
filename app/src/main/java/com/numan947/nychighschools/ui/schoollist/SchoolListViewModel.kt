package com.numan947.nychighschools.ui.schoollist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numan947.nychighschools.data.local.SchoolDetailsRepository
import com.numan947.nychighschools.data.network.ApiService
import com.numan947.nychighschools.domain.HighSchoolListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolListViewModel @Inject constructor(
    private val apiService: ApiService,
    private val schoolDetailsRepository: SchoolDetailsRepository
) : ViewModel() {
    private var isLoading = MutableLiveData<Boolean>()
    private val _hsMutableList = MutableLiveData<ArrayList<HighSchoolListItem>?>()
    val highSchoolListLiveData: LiveData<ArrayList<HighSchoolListItem>?> get() = _hsMutableList
    private var _savedSchoolsDbn: List<String> = emptyList()
    val savedSchoolsDbn: List<String> get() = _savedSchoolsDbn
    private var fetchedData: ArrayList<HighSchoolListItem>? = null


    fun getHighSchoolsFromNetwork(forceNetwork: Boolean = false) {
        println("getHighSchoolsFromNetwork")
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                // fetch saved schools from local db
                _savedSchoolsDbn = schoolDetailsRepository.getSavedSchools()
                Log.v("SchoolListViewModel", "Saved schools: ${_savedSchoolsDbn.size}")
                if (fetchedData == null || forceNetwork) {
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
                        }else{
                            school.isSaved = false
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

    fun getHighSchoolsFromDb() {
        println("getHighSchoolsFromDb")
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val  savedSchools = schoolDetailsRepository.getAllSchoolDetails()

                val highSchoolItemList:ArrayList<HighSchoolListItem> = ArrayList()
                savedSchools.forEach {
                    highSchoolItemList.add(HighSchoolListItem.fromSchoolDetailsEntity(it))
                }
                _hsMutableList.postValue(highSchoolItemList)
                isLoading.postValue(false)
            } catch (e: Exception) {
                e.printStackTrace()
                isLoading.postValue(false)
                _hsMutableList.postValue(null)
            }
        }
    }
}