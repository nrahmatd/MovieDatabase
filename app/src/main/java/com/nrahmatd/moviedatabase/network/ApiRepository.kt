package com.nrahmatd.moviedatabase.network

import com.nrahmatd.moviedatabase.network.retrofit.RetrofitHelper


class ApiRepository {
    private var apiServices: ApiServices =
        RetrofitHelper.invoke(
            Api.BASE_URL
        ).create(ApiServices::class.java)

    suspend fun getMovies() = apiServices.getMovies()
}
