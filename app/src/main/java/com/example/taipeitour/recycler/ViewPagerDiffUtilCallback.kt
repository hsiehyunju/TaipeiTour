package com.example.taipeitour.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.taipeitour.data.ViewPagerItem

/**
 * 圖片 View Pager 判斷 Item 是否一致的 Callback
 */
class ViewPagerDiffUtilCallback : DiffUtil.ItemCallback<ViewPagerItem>() {
    override fun areItemsTheSame(oldItem: ViewPagerItem, newItem: ViewPagerItem): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: ViewPagerItem, newItem: ViewPagerItem): Boolean {
        return oldItem.url == newItem.url
    }
}