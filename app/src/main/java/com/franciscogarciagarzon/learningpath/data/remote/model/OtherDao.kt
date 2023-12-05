package com.franciscogarciagarzon.learningpath.data.remote.model


import com.google.gson.annotations.SerializedName

data class OtherDao(
    @SerializedName("dream_world")
    val dreamWorld: DreamWorldDao,
    @SerializedName("home")
    val home: HomeDao,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkDao
)