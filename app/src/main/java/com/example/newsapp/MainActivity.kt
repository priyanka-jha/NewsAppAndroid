package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(), NewsAdapter.NewsItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsRV.layoutManager = LinearLayoutManager(this)
        val newsAdapter: NewsAdapter = NewsAdapter(fetchData(), this)
        newsRV.adapter=newsAdapter
    }

    private fun fetchData(): ArrayList<String> {
        val newsList = ArrayList<String>()
        for (i in 0 until 100){
            newsList.add("Iten $i")
        }
        return newsList
    }

    override fun onItemCliced(item: String) {
        Toast.makeText(this, "Item clicked $item", Toast.LENGTH_SHORT).show()
    }
}