package com.example.taipeitour.data

/**
 * 儲存旅遊景點資訊的物件
 */
data class TourItem(
    val id: Int,
    val name: String,
    val introduction: String,
    val remind: String,
    val url: String,
    val address: String,
    val tel: String,
    val images: List<String>
) {

    override fun toString(): String {
        return name
    }

}
