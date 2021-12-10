package com.example.news.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.news.R
import com.example.news.UrlActivity
import com.example.news.api.model.Article
import com.example.news.databinding.ListItemView1Binding
import com.example.news.databinding.ListItemView2Binding


class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = arrayListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_view_1,
                parent,
                false)
            ViewHolder1(view)
        } else {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_view_2, parent, false)
            ViewHolder2(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 0) {
            (holder as ViewHolder1).bindData(items[position])
        } else {
            (holder as ViewHolder2).bindData(items[position])
        }
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }


    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedItems: ArrayList<Article>) {
        items = updatedItems
        notifyDataSetChanged()

    }

    class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemView2Binding.bind(itemView)

        private val title: TextView = binding.title
        private val image: ImageView = binding.image
        private val date: TextView = binding.datePublished
        private val source: TextView = binding.source

        fun bindData(dataList: Article) {
            title.text = dataList.title
            Glide.with(itemView.context).load(dataList.urlToImage).fitCenter()
                .transform(RoundedCorners(20)).into(image)

            date.text = dataList.publishedAt.substring(0, 10)
            source.text = dataList.source.name
            itemView.setOnClickListener {
                val intent = Intent(it.context, UrlActivity::class.java)
                intent.putExtra("URL", dataList.url)
                intent.putExtra("Heading", dataList.source.name)
                it.context.startActivity(intent)
            }

        }
    }

    class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemView1Binding.bind(itemView)

        private val title: TextView = binding.title
        private val image: ImageView = binding.image
        private val date: TextView = binding.datePublished
        private val source: TextView = binding.source

        fun bindData(dataList: Article) {
            title.text = dataList.title
            Glide.with(itemView.context).load(dataList.urlToImage).fitCenter()
                .transform(RoundedCorners(20)).into(image)

            date.text = dataList.publishedAt.substring(0, 10)
            source.text = dataList.source.name

            itemView.setOnClickListener {
                val intent = Intent(it.context, UrlActivity::class.java)
                intent.putExtra("URL", dataList.url)
                intent.putExtra("Heading", dataList.source.name)
                it.context.startActivity(intent)

//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(dataList.url))
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                it.context.startActivity(intent)
            }

        }
    }

}







