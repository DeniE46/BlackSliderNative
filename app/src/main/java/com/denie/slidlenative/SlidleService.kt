package com.denie.slidlenative

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import okhttp3.logging.HttpLoggingInterceptor



interface SlidleService {

@GET("getfeaturedprojects")
fun getWorkspaces(): Call<MutableList<WorkspaceModel>>

companion object{
    private const val BASE_URL = "http://slidle.com/content/"




    fun createService():SlidleService{

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(SlidleService::class.java)
    }

}

}