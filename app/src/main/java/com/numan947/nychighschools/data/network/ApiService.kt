package com.numan947.nychighschools.data.network

import com.numan947.nychighschools.domain.HighSchoolList
import com.numan947.nychighschools.domain.HighSchoolScores
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getAllHighSchools(): Response<HighSchoolList>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getAllSATScores(
        @Query("dbn") dbn: String
    ): Response<HighSchoolScores>
}