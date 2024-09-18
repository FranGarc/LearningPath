package com.franciscogarciagarzon.learningpath.data.remote.model

import com.google.gson.annotations.SerializedName

data class StatDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)