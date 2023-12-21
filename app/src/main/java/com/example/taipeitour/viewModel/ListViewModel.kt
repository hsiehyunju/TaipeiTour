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
        fetchData()
    }

    private fun fetchData()
    {
        // TODO: 透過 Api 抓取資料
        var list = List(1) { index ->
            TourItem(
                id = index,
                name = "景點 $index",
                introduction = "景點 $index 的介紹",
                remind = "景點 $index 的提醒",
                url = "https://example.com",
                address = "景點 $index 的地址",
                tel = "景點 $index 的電話",
                images = listOf("https://www.travel.taipei/image/221368")
            )
        }

        _tourList.postValue(list)
    }
}