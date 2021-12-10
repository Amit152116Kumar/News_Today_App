package com.example.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager
import com.example.news.api.model.Article
import com.example.news.retrofit.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FragmentViewModel(application: Application) :AndroidViewModel(application) {

    private val repo = Repository(application)


    private var country =
        PreferenceManager.getDefaultSharedPreferences(application).getString("country", null)
    private lateinit var lang: String
    private val retrofitClient = RetrofitClient()

    fun topHeadlines(category: String): Flow<ArrayList<Article>> = flow {
        val response = retrofitClient.api.getTopHeadlines(category = category,
            pageSize = 100,
            country = country)
        val articles: ArrayList<Article>? = response.body()?.articles
        if (articles != null) {
            emit(articles)
        }
    }


}