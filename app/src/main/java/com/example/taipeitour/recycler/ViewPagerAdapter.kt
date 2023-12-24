package com.example.taipeitour.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeitour.data.ViewPagerItem
import com.example.taipeitour.databinding.ViewpagerItemBinding

class ViewPagerAdapter: ListAdapter<ViewPagerItem, ViewPagerAdapter.ViewPagerHolder>(ViewPagerDiffUtilCallback()) {

    class ViewPagerHolder(private val binding: ViewpagerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(data: ViewPagerItem)
        {
            Glide.with(binding.root)
                .load(data.url)
                .into(binding.viewpagerItemImageview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewpagerItemBinding.inflate(layoutInflater, parent, false)
        return ViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.binding(getItem(position))
    }
}