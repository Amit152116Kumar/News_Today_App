package com.example.news

import android.app.Application
import androidx.preference.PreferenceManager
import com.example.news.api.model.Article
import com.example.news.api.model.SourceX
import com.example.news.retrofit.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class Repository(application: Application) {
    private var country =
        PreferenceManager.getDefaultSharedPreferences(application).getString("country", null)
    private val lang = Locale.getDefault().displayLanguage
    private val retrofitClient = RetrofitClient()

    fun topHeadlines(
        category: String? = null,
        source: String? = null,
        page: Int? = null,
    ): Flow<List<Article>> = flow {
        val response = retrofitClient.api.getTopHeadlines(category = category,
            pageSize = 20,
            country = country,
            sources = source,
            page = page)
        val articles: List<Article>? = response.body()?.articles
        if (articles != null) {
            emit(articles)
        }
    }

    fun getEverything(
        q: String? = null,
        qInTitle: String? = null,
        source: String? = null,
        sortBy: String? = null,
        page: Int? = null,
        fromDate: String? = null,
        toDate: String? = null,
    ): Flow<List<Article>> = flow {
        val response = retrofitClient.api.getEverything(lang = lang,
            pageSize = 20,
            page = page,
            qInTitle = qInTitle,
            query = q,
            sortBy = sortBy,
            fromDate = fromDate,
            toDate = toDate,
            sources = source)
        val articles = response.body()?.articles
        if (articles != null) {
            emit(articles)
        }
    }

    fun getSources(category: String? = null): Flow<List<SourceX>> = flow {
        val response =
            retrofitClient.api.getSource(country = country, category = category, lang = lang)
        val sources = response.body()?.sources
        if (sources != null) {
            emit(sources)
        }
    }
}