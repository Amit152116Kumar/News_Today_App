package com.example.news.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.news.R
import com.example.news.api.model.Article
import com.example.news.databinding.ListItemView1Binding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var items = listOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_view_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedItems: List<Article>) {
        items = updatedItems
        notifyDataSetChanged()

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemView1Binding.bind(itemView)

        private val title: TextView = binding.title
        private val image: ImageView = binding.image
        private val date: TextView = binding.datePublished
        private val source: TextView = binding.source

        fun bindData(dataList: Article) {
            title.text = dataList.title
            Glide.with(itemView.context).load(dataList.urlToImage).fitCenter()
                .transform(RoundedCorners(20)).into(image)

            date.text = dataList.publishedAt.substring(0,10)
            source.text = dataList.source.name
            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(dataList.url))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                it.context.startActivity(intent)
            }

        }
    }
}




