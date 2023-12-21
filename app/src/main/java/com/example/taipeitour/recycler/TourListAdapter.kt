package com.example.taipeitour.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeitour.data.TourItem
import com.example.taipeitour.databinding.TourlistItemBinding

/**
 * Tour List RecyclerView 使用的 Adapter
 */
class TourListAdapter: ListAdapter<TourItem, TourListAdapter.TourViewHolder>(TourDiffUtilCallback())  {

    class TourViewHolder(private val binding: TourlistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: TourItem) {
            this.binding.apply {
                // 設定圖片，使用 Glide 顯示
                data.images.firstOrNull()?.let { url ->
                    Glide.with(this.root)
                        .load(url)
                        .into(listImage)
                }

                // 設定景點資訊
                listTitle.text = data.toString()
                listRemind.text = data.remind
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TourlistItemBinding.inflate(layoutInflater, parent, false)
        return TourViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}