package com.example.newsapp

import android.app.ProgressDialog
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.util.*

class MainActivity : AppCompatActivity(), NewsAdapter.NewsItemClickListener {

    companion object{
        private val TAG :String? = MainActivity::class.java.simpleName
    }
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()

    }

    private fun fetchData(): ArrayList<NewsModel> {

        val url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"
        val newsList = ArrayList<NewsModel>()
        newsList.clear()

        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Downloading")
        progressDialog!!.setMessage("Loading news, please wait")
        progressDialog!!.show()

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null, Response.Listener {

            println("On Response")

            progressDialog!!.dismiss()

            val articlesArray: JSONArray = it.getJSONArray("articles")

            for (i in 0 until articlesArray.length()){

                val newsJsonObject = articlesArray.getJSONObject(i)
                val newsModel = NewsModel(newsJsonObject.getString("title"),
                    newsJsonObject.getString("author"),
                    newsJsonObject.getString("url"),
                    newsJsonObject.getString("urlToImage"))

                newsList.add(newsModel)
            }

            newsRV.layoutManager = LinearLayoutManager(this)
            val newsAdapter = NewsAdapter(newsList, this)
            newsRV.adapter=newsAdapter
            newsAdapter.notifyDataSetChanged()

        }, Response.ErrorListener {

            progressDialog!!.dismiss()
            Log.i(TAG,it.localizedMessage)

        })

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)


        return newsList
    }

    override fun onItemCliced(item: NewsModel) {

        val builder: CustomTabsIntent.Builder =  CustomTabsIntent.Builder()
        val colorInt: Int = Color.parseColor("#FF3700B3")
        builder.setToolbarColor(colorInt)
        val customTabsIntent: CustomTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url));


    }
}