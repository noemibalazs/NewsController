package com.example.newscontroller.network


import com.example.newscontroller.data.NewsBody
import com.example.newscontroller.utils.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("search?&show-fields=all&show-tags=all")
    fun getBusinessNews(@Query("api-key") key: String, @Query("q") search: String, @Query("tag") orderBy: String) : Call<NewsBody>

    @GET("search?&show-fields=all&show-tags=all")
    fun getSportNews(@Query("api-key") key: String, @Query("q") search: String ,@Query("tag") orderBy: String) : Call<NewsBody>

    @GET("search?&show-fields=all&show-tags=all")
    fun getPoliticsNews(@Query("api-key") key: String, @Query("q") search: String , @Query("tag") orderBy: String) : Call<NewsBody>

    companion object{

        fun getRetrofitInstance():NewsService{

            val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build().create(NewsService::class.java)
        }
    }

}