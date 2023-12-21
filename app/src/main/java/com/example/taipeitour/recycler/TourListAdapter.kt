package com.example.taipeitour.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeitour.R
import com.example.taipeitour.data.TourItem
import com.example.taipeitour.databinding.TourlistItemBinding
import java.util.ArrayList

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

                this.root.setOnClickListener {
                    var bundle = Bundle().apply {
                        putString("title", data.toString())
                        putString("introduction", data.introduction)
                        putString("address", data.address)
                        putString("tel", data.tel)
                        putString("url", data.url)
                        putStringArrayList("images", ArrayList(data.images))
                    }

                    Navigation.findNavController(binding.root).navigate(R.id.action_listFragment_to_detailFragment, bundle)
                }
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