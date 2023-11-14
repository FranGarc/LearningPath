package com.franciscogarciagarzon.learningpath.ui.model

import com.franciscogarciagarzon.learningpath.domain.model.StatDto
import com.franciscogarciagarzon.learningpath.domain.model.StatsDto

data class StatsUi(
    val attack: StatUi = StatUi(),
    val defense: StatUi = StatUi(),
    val hp: StatUi = StatUi(),
    val specialAttack: StatUi = StatUi(),
    val specialDefense: StatUi = StatUi(),
    val speed: StatUi = StatUi(),
)

fun StatsDto.toStatsUi() = StatsUi(
    attack = this.attack.toStatUi(),
    defense = this.defense.toStatUi(),
    hp = this.hp.toStatUi(),
    speed = this.speed.toStatUi(),
    specialAttack = this.specialAttack.toStatUi(),
    specialDefense = this.specialDefense.toStatUi(),
)

fun StatDto.toStatUi() = StatUi(
    name = this.name,
    value = this.value
)

data class StatUi(val name: String = "", val value: Int = -1)
