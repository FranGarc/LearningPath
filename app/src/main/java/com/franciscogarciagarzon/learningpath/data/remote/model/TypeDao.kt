package com.franciscogarciagarzon.learningpath.data.remote.model


import com.google.gson.annotations.SerializedName

data class TypeDao(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeX
)


fun List<TypeDao>.toDomainTypes(): List<String> {
    val numberOfTypes = this.size
    val listOfTypes = mutableListOf<String>()
    listOfTypes.add(this.first().type.name)
    if (numberOfTypes > 1) {
        listOfTypes.add(this.last().type.name)
    }
    return listOfTypes
}