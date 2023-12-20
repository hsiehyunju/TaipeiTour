package com.example.taipeitour.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.taipeitour.data.TourItem

/**
 * Tour List Adapter 判斷物件是否一樣的 Item Callback
 */
class TourDiffUtilCallback: DiffUtil.ItemCallback<TourItem>() {

    override fun areItemsTheSame(oldItem: TourItem, newItem: TourItem): Boolean {
        // 如果 ID 一樣，等於是相同物件
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TourItem, newItem: TourItem): Boolean {

        // 先判斷 Images 有沒有變動
        if (oldItem.images.count() == newItem.images.count())
        {
            for (index in oldItem.images.indices)
            {
                if (oldItem.images[index] == newItem.images[index])
                {
                    continue
                }
                else
                {
                    return false
                }
            }
        }
        else
        {
            return false
        }

        // 判斷其他內容是否有變動
        return oldItem.toString() == newItem.toString() &&
                oldItem.introduction == newItem.introduction &&
                oldItem.remind == newItem.remind &&
                oldItem.url == newItem.url &&
                oldItem.address == newItem.address &&
                oldItem.tel == newItem.tel
    }
}