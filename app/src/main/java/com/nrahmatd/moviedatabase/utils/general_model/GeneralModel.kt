package com.nrahmatd.moviedatabase.utils.general_model

import com.google.gson.annotations.SerializedName

data class GeneralModel(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)
