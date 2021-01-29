package com.example.news.retrofit
import com.example.news.api.model.NewsResponse
import com.example.news.api.model.SourceResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FetchDataRetrofit{

    private val key: String
        get() = "b05f2a51b7ec44a0bdac8238b9f798f3"

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country:String?=null,
        @Query("category") category:String?=null,
        @Query("sources") sources:String?=null,
        @Query("pageSize") pageSize:Int?=null,
        @Query("page") page:Int?=null,
        @Query("apiKey") apiKey:String=key
    ): Response<NewsResponse>


    @GET("everything")
    suspend fun getEverything(
        @Query("q") query:String?=null,
        @Query("qInTitle") qInTitle:String?=null,
        @Query("sources") sources:String?=null,
        @Query("from") fromDate:String?=null,
        @Query("to") toDate:String?=null,
        @Query("language") lang:String= "en",
        @Query("sortBy") sortBy:String?=null,
        @Query("pageSize") pageSize:Int?=null,
        @Query("page") page:Int?=null,
        @Query("apiKey") apiKey:String=key

    ): Response<NewsResponse>

    @GET("sources")
    suspend fun getSource(
        @Query("category") category: String?=null,
        @Query("language") lang:String?=null,
        @Query("country") country:String?=null,
        @Query("apiKey") apiKey:String=key
    ): Response<SourceResponse>


}

