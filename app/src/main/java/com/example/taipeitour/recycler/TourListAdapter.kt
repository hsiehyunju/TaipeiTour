package com.example.taipeitour.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taipeitour.data.TourItem
import com.example.taipeitour.databinding.TourlistItemBinding

/**
 * Tour List RecyclerView 使用的 Adapter
 */
class TourListAdapter: ListAdapter<TourItem, TourListAdapter.TourViewHolder>(TourDiffUtilCallback())  {

    class TourViewHolder(private val binding: TourlistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: TourItem) {
            this.binding.apply {
                TODO("將 Tour Item 資訊綁定到 UI 上")
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