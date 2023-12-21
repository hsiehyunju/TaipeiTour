package com.example.taipeitour.repository

import com.example.taipeitour.data.TourResponse
import com.example.taipeitour.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TourRepository {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.travel.taipei/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var apiService: ApiService

    init {
        apiService = retrofit.create(ApiService::class.java)
    }

    /**
     * 取得 Tour 資料
     * @param language 語言
     * @param page 要抓取的頁數
     */
    suspend fun getTourDataRaw(language: String, page: Int): Response<TourResponse> {
        return withContext(Dispatchers.IO) {
            apiService.getTourDataRaw(language = language, page = page)
        }
    }
}