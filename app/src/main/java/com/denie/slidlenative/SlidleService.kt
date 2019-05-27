package com.denie.slidlenative

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query


interface SlidleService {

    @GET("getfeaturedprojects")
    suspend fun getWorkspaces(): MutableList<WorkspaceModel>

    @GET("getpages/{workId}?flat=true")
    suspend fun getPresentationsPerWorkspace(@Path("workId") workId:Int): MutableList<PresentationModel>

    companion object {
        private const val BASE_URL = "http://slidle.com/content/"


        fun createService(): SlidleService {

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