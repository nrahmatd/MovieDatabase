package com.nrahmatd.moviedatabase.network.retrofit

import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun invoke(baseUrl: String): Retrofit {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val httpClient = OkHttpClient.Builder()
        httpClient.dispatcher(dispatcher)

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
}
