package com.example.taipeitour.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taipeitour.data.TourItem

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val _tourList = MutableLiveData<List<TourItem>>()
    val tourList: LiveData<List<TourItem>> get() = _tourList

    init {
        // 初始化時下載數據
        fetchData();
    }

    private fun fetchData()
    {
        // TODO: 透過 Api 抓取資料
    }
}