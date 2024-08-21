package com.numan947.nychighschools.data.network

import com.numan947.nychighschools.domain.HighSchools
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getAllHighSchools(): Response<HighSchools>
}