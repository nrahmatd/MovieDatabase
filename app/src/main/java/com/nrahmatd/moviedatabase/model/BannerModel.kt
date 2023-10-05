package com.nrahmatd.moviedatabase.model

import com.google.gson.annotations.SerializedName

data class BannerModel(
    @SerializedName("data")
    val data: Data
) {
    data class Data(
        @SerializedName("banner_url")
        val bannerUrl: String
    )
}
