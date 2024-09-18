package com.franciscogarciagarzon.learningpath.data.remote.model


import com.google.gson.annotations.SerializedName

data class AbilityWrapperDto(
    @SerializedName("ability")
    val ability: AbilityDto,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int
)