package com.example.taipeitour.service

import com.example.taipeitour.data.TourResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("open-api/{lang}/Attractions/All")
    @Headers("Accept: application/json")
    suspend fun getTourDataRaw(
        @Path("lang") language: String = "zh-tw",
        @Query("page") page: Int = 1
    ): Response<TourResponse>
}