package com.numan947.nychighschools.ui.schoollist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numan947.nychighschools.data.network.ApiService
import com.numan947.nychighschools.domain.HighSchoolList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SchoolListViewModel @Inject constructor(
    private val apiService: ApiService
):ViewModel() {
    public var isLoading = MutableLiveData<Boolean>()
    private val _data = MutableLiveData<Response<HighSchoolList>>()
    val data:LiveData<Response<HighSchoolList>>get() = _data

    fun getHighSchools(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = apiService.getAllHighSchools()
            if (response.isSuccessful){
                _data.postValue(response)
                isLoading.postValue(false)
            }else{
                // handle error
            }
        }
    }

}