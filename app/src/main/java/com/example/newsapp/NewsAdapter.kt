package com.example.newsapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.NewsAdapter.*

class NewsAdapter(private val newsArrayList: ArrayList<NewsModel>, private val listener: NewsItemClickListener ):
        RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        Log.i("NewsAdapter","onCreateViewHolder" )

        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_content,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener(View.OnClickListener {
            listener.onItemCliced(newsArrayList[viewHolder.adapterPosition])
        })
        return viewHolder
    }

    override fun getItemCount(): Int {

        Log.i("NewsAdapter","getItemCount" )

        return newsArrayList.size
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        Log.i("NewsAdapter","onBindViewHolder" )

        val currentItem = newsArrayList[position]
        holder.textNewsTitle.text = currentItem.title
        holder.textNewAuthor.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.imageNews)


    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textNewsTitle = itemView.findViewById<TextView>(R.id.textTitle)
        val textNewAuthor = itemView.findViewById<TextView>(R.id.textAuthor)
        val imageNews = itemView.findViewById<ImageView>(R.id.imageNews)

    }

    interface NewsItemClickListener {
        fun onItemCliced(item:NewsModel)
    }
}