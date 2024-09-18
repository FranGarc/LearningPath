package com.franciscogarciagarzon.learningpath.data.remote.model

import com.google.gson.annotations.SerializedName

data class StatWrapperDto (
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("stat")
    val stat: StatDto
)