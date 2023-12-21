package com.example.taipeitour.data


data class TourResponse(
    val total: Int,
    val data: List<DataList>
)

data class DataList(
    val id: Int,
    val name: String,
    val introduction: String,
    val remind: String,
    val url: String,
    val address: String,
    val tel: String,
    val images: List<TourImage>
)

data class TourImage(
    val src: String
)