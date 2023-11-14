package com.franciscogarciagarzon.learningpath.domain.model

data class StatsDto(
    val attack: StatDto = StatDto(),
    val defense: StatDto = StatDto(),
    val hp: StatDto = StatDto(),
    val specialAttack: StatDto = StatDto(),
    val specialDefense: StatDto = StatDto(),
    val speed: StatDto = StatDto(),
)
