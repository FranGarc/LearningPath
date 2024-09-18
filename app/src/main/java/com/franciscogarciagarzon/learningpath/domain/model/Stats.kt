package com.franciscogarciagarzon.learningpath.domain.model

data class Stats(
    val attack: Stat = Stat(),
    val defense: Stat = Stat(),
    val hp: Stat = Stat(),
    val specialAttack: Stat = Stat(),
    val specialDefense: Stat = Stat(),
    val speed: Stat = Stat(),
)
