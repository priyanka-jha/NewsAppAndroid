package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.NewsAdapter.*

class NewsAdapter(private val newsArrayList: ArrayList<String>, private val listener: NewsItemClickListener ): RecyclerView.Adapter<NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_content,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener(View.OnClickListener {
            listener.onItemCliced(newsArrayList[viewHolder.adapterPosition])
        })
        return viewHolder
    }

    override fun getItemCount(): Int {
        return newsArrayList.size
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = newsArrayList[position]
        holder.textNewsTitle.text = currentItem
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textNewsTitle = itemView.findViewById<TextView>(R.id.textTitle)


    }

    interface NewsItemClickListener {
        fun onItemCliced(item:String)
    }
}