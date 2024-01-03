package com.franciscogarciagarzon.learningpath.ui.model

import com.franciscogarciagarzon.learningpath.domain.model.AbilityDto
import com.franciscogarciagarzon.learningpath.domain.model.URL
import com.franciscogarciagarzon.learningpath.ui.extensions.capitalizeLP

data class AbilityUi(
    val name: String,
    val url: URL
) {
    fun printableName(): String {
        return this.name.replace("-", " ").capitalizeLP()
    }
}

fun AbilityDto.toAbilityUi() = AbilityUi(
    name = this.name,
    url = this.abilityUrl
)
