package com.example.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager
import com.example.news.api.model.Article
import com.example.news.api.model.SourceX
import com.example.news.retrofit.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FragmentViewModel(application: Application) :AndroidViewModel(application) {
    private var country=PreferenceManager.getDefaultSharedPreferences(application).getString("country",null)
    private val retrofitClient=RetrofitClient()

    fun topHeadlines(category:String): Flow<List<Article>> = flow{
        val response = retrofitClient.api.getTopHeadlines(category = category,
                pageSize = 100,
                country = country)
        val articles: List<Article>? =response.body()?.articles
        if (articles!=null){
            emit(articles)
        }
    }
    fun getEverything():Flow<List<Article>> = flow {

    }
    fun getSources():Flow<List<SourceX>> = flow {

    }


}