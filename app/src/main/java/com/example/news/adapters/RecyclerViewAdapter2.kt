package com.example.news.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.api.model.SourceX

class RecyclerViewAdapter2 : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var item = listOf<SourceX>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_view_2, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return item.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}