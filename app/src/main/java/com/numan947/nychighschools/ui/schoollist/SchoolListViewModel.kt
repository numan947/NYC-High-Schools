package com.numan947.nychighschools.ui.schoollist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numan947.nychighschools.data.network.ApiService
import com.numan947.nychighschools.domain.HighSchools
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SchoolListViewModel @Inject constructor(
    private val apiService: ApiService
):ViewModel() {
    private val _data = MutableLiveData<Response<HighSchools>>()
    val data:LiveData<Response<HighSchools>>get() = _data

    fun getHighSchools(){
        viewModelScope.launch {
            val response = apiService.getAllHighSchools()
            if (response.isSuccessful){
                _data.postValue(response)
            }else{
                // handle error
            }
        }
    }

}