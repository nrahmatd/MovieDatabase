package com.nrahmatd.moviedatabase.utils

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.nrahmatd.moviedatabase.network.exception.ApiException
import com.nrahmatd.moviedatabase.utils.general_model.GeneralModel
import java.lang.reflect.Type
import okhttp3.ResponseBody
import retrofit2.Response

fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun setResponse(liveData: MutableLiveData<ResponseHelper>, code: Int, value: Any) {
    liveData.value = ResponseHelper(code, value)
}

fun setErrorBody(errorBody: ResponseBody): GeneralModel {
    return GsonBuilder().registerTypeAdapter(
        GeneralModel::class.java,
        ErrorDeserializer()
    ).create().fromJson(errorBody.string(), GeneralModel::class.java)
}

fun <T> setRequest(
    liveData: MutableLiveData<ResponseHelper>,
    request: Response<T>,
    code_request: Int
) {
    when (request.code()) {
        ResponseHelper.OK, ResponseHelper.Created -> {
            println("response body.. $liveData - ${request.body()}")
            setResponse(liveData, code_request, request.body()!!)
        }
        ResponseHelper.BadRequest -> {
            val error = setErrorBody(request.errorBody()!!)
            setResponse(liveData, ResponseHelper.BadRequest, error)
        }
        ResponseHelper.Unauthorized -> {
            val error = setErrorBody(request.errorBody()!!)
            setResponse(liveData, ResponseHelper.BadRequest, error)
        }
        ResponseHelper.NotFound -> {
            val error = setErrorBody(request.errorBody()!!)
            setResponse(liveData, ResponseHelper.NotFound, error)
        }
        ResponseHelper.Timeout -> {
            val error = setErrorBody(request.errorBody()!!)
            setResponse(liveData, ResponseHelper.Timeout, error)
        }
        else -> {
            throw ApiException(request.code().toString())
        }
    }
}

class ErrorDeserializer : JsonDeserializer<GeneralModel?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GeneralModel {
        return Gson().fromJson(json, GeneralModel::class.java)
    }
}

