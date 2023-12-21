package com.example.taipeitour.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.taipeitour.data.TourItem
import com.example.taipeitour.repository.TourRepository
import com.example.taipeitour.tools.LogTool
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val _tourListCache: MutableList<TourItem> = mutableListOf()

    private val _tourList = MutableLiveData<List<TourItem>>()
    val tourList: LiveData<List<TourItem>> get() = _tourList

    private val repository = TourRepository()

    /** 儲存呼叫 Api 次數  */
    private var _callApiCount = 0
    /** 單次 Api 回傳的最大資料量 */
    private val _maxDataPerPage = 30

    init {
        // 初始化時下載數據
        fetchData()
    }

    /**
     * 抓取 Server 資料
     */
    private fun fetchData()
    {

        viewModelScope.launch {

            val numberOfPage = _callApiCount + 1

            try {
                val response = repository.getTourDataRaw("zh-tw", numberOfPage)

                if (response.isSuccessful)
                {
                    LogTool.i("response is successful, ths page is " + numberOfPage)
                    response.body()?.let {
                        for (item in it.data)
                        {
                            val tourItem = TourItem(
                                id = item.id,
                                name = item.name,
                                introduction = item.introduction,
                                remind = item.remind,
                                url = item.url,
                                tel = item.tel,
                                address = item.address,
                                images = item.images.map { image -> image.src }
                            )

                            _tourListCache.indexOfFirst { it.id == tourItem.id }.let { index ->
                                if (index != -1)
                                {
                                    _tourListCache.set(index, tourItem)
                                }
                                else
                                {
                                    _tourListCache.add(tourItem)
                                }
                            }
                        }

                        _tourList.postValue(_tourListCache)
                        _callApiCount++

                        if (it.total != _tourListCache.size)
                        {
                            fetchData()
                        }
                    }
                }
                else
                {
                    LogTool.e("response is not successful")
                }

            } catch (e: HttpException) {
                val url = e.response()?.raw()?.request()?.url().toString()
                val errorCode = e.code()
                val errorMessage = e.message()
                // 顯示錯誤消息，同時顯示 URL
                println("HTTP error code: $errorCode, message: $errorMessage, URL: $url")
            }
        }
    }
}