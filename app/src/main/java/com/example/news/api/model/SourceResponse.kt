package com.example.news.api.model


import com.google.gson.annotations.SerializedName

data class SourceResponse(
    @SerializedName("sources")
    val sources: List<SourceX> = listOf<SourceX>(),
    @SerializedName("status")
    val status: String = ""
)