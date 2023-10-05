package com.nrahmatd.moviedatabase.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MovieModel(
    @SerializedName("entries")
    val entries: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>
) {
    @Keep
    data class Result(
        @SerializedName("_id")
        val _id: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("originalTitleText")
        val originalTitleText: OriginalTitleText,
        @SerializedName("primaryImage")
        val primaryImage: PrimaryImage?,
        @SerializedName("releaseDate")
        val releaseDate: ReleaseDate,
        @SerializedName("releaseYear")
        val releaseYear: ReleaseYear,
        @SerializedName("titleText")
        val titleText: TitleText,
        @SerializedName("titleType")
        val titleType: TitleType
    ) {
        @Keep
        data class OriginalTitleText(
            @SerializedName("text")
            val text: String,
            @SerializedName("__typename")
            val typename: String
        )

        @Keep
        data class PrimaryImage(
            @SerializedName("caption")
            val caption: Caption,
            @SerializedName("height")
            val height: Int,
            @SerializedName("id")
            val id: String,
            @SerializedName("__typename")
            val typename: String,
            @SerializedName("url")
            val url: String?,
            @SerializedName("width")
            val width: Int
        ) {
            @Keep
            data class Caption(
                @SerializedName("plainText")
                val plainText: String,
                @SerializedName("__typename")
                val typename: String
            )
        }

        @Keep
        data class ReleaseDate(
            @SerializedName("day")
            val day: Int,
            @SerializedName("month")
            val month: Int,
            @SerializedName("__typename")
            val typename: String,
            @SerializedName("year")
            val year: Int
        )

        @Keep
        data class ReleaseYear(
            @SerializedName("endYear")
            val endYear: Any,
            @SerializedName("__typename")
            val typename: String,
            @SerializedName("year")
            val year: Int
        )

        @Keep
        data class TitleText(
            @SerializedName("text")
            val text: String,
            @SerializedName("__typename")
            val typename: String
        )

        @Keep
        data class TitleType(
            @SerializedName("canHaveEpisodes")
            val canHaveEpisodes: Boolean,
            @SerializedName("categories")
            val categories: List<Category>,
            @SerializedName("displayableProperty")
            val displayableProperty: DisplayableProperty,
            @SerializedName("id")
            val id: String,
            @SerializedName("isEpisode")
            val isEpisode: Boolean,
            @SerializedName("isSeries")
            val isSeries: Boolean,
            @SerializedName("text")
            val text: String,
            @SerializedName("__typename")
            val typename: String
        ) {
            @Keep
            data class Category(
                @SerializedName("__typename")
                val typename: String,
                @SerializedName("value")
                val value: String
            )

            @Keep
            data class DisplayableProperty(
                @SerializedName("__typename")
                val typename: String,
                @SerializedName("value")
                val value: Value
            ) {
                @Keep
                data class Value(
                    @SerializedName("plainText")
                    val plainText: String,
                    @SerializedName("__typename")
                    val typename: String
                )
            }
        }
    }
}