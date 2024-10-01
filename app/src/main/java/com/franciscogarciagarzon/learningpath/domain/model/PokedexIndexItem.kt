package com.franciscogarciagarzon.learningpath.domain.model

import com.franciscogarciagarzon.learningpath.data.remote.model.extractId

data class PokedexIndexItem(
    val id: Int,
    val name: String,
    val url: URL
){
    fun isValid(): Boolean{
        return name.isNotBlank().and(url.isNotBlank()).and(idIsValid())
    }

     private fun idIsValid() : Boolean{
        return id == url.extractId()
    }
}



