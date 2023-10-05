package com.nrahmatd.moviedatabase.network

import com.nrahmatd.moviedatabase.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiServices {

    @GET(Api.MOVIES)
    suspend fun getMovies(
        @Header("X-RapidAPI-Key") key: String = Api.RAPIDAPI_Key,
        @Header("X-RapidAPI-Host") host: String = Api.RAPIDAPI_Host,
    ): Response<MovieModel>

}
