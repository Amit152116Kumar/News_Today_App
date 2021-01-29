package com.example.news

import com.example.news.retrofit.RetrofitClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val retroapi=RetrofitClient()

    @Test
    fun  top_headlines(){
        runBlocking{
            val newsResponse = retroapi.api.getTopHeadlines(pageSize = 100, country = "in",category = "business")
            val a=newsResponse.body()?.articles
            assertNotNull(a)
        }

    }
    @Test
    fun getEverything(){
        runBlocking{
            val newsResponse = retroapi.api.getEverything("", "cricket", "", "", "", "fr")
            assertNotNull(newsResponse.body()?.articles)
        }
    }
    @Test
    fun getsource(){
        runBlocking {
            val sourceResponse =retroapi.api.getSource()
            assertNotNull(sourceResponse.body()?.sources)
        }
        }
}