package com.franciscogarciagarzon.learningpath.ui.model

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.franciscogarciagarzon.learningpath.ui.theme.ColorBug
import com.franciscogarciagarzon.learningpath.ui.theme.ColorDark
import com.franciscogarciagarzon.learningpath.ui.theme.ColorDragon
import com.franciscogarciagarzon.learningpath.ui.theme.ColorElectric
import com.franciscogarciagarzon.learningpath.ui.theme.ColorFairy
import com.franciscogarciagarzon.learningpath.ui.theme.ColorFighting
import com.franciscogarciagarzon.learningpath.ui.theme.ColorFire
import com.franciscogarciagarzon.learningpath.ui.theme.ColorFlying
import com.franciscogarciagarzon.learningpath.ui.theme.ColorGhost
import com.franciscogarciagarzon.learningpath.ui.theme.ColorGrass
import com.franciscogarciagarzon.learningpath.ui.theme.ColorGround
import com.franciscogarciagarzon.learningpath.ui.theme.ColorIce
import com.franciscogarciagarzon.learningpath.ui.theme.ColorNormal
import com.franciscogarciagarzon.learningpath.ui.theme.ColorPoison
import com.franciscogarciagarzon.learningpath.ui.theme.ColorPsychic
import com.franciscogarciagarzon.learningpath.ui.theme.ColorRock
import com.franciscogarciagarzon.learningpath.ui.theme.ColorSteel
import com.franciscogarciagarzon.learningpath.ui.theme.ColorWater

enum class PokemonType {
    normal, fire, water, electric, grass, ice, fighting, poison, ground, flying, psychic, bug, rock, ghost, dragon, dark, steel, fairy
}

fun String.toTypeUi(): TypeUi {
    Log.d("TypeUi", "typeName: $this")
    return when (this) {
        PokemonType.normal.name -> TypeUi.Normal
        PokemonType.fire.name -> TypeUi.Fire
        PokemonType.water.name -> TypeUi.Water
        PokemonType.electric.name -> TypeUi.Electric
        PokemonType.grass.name -> TypeUi.Grass
        PokemonType.ice.name -> TypeUi.Ice
        PokemonType.fighting.name -> TypeUi.Fighting
        PokemonType.poison.name -> TypeUi.Poison
        PokemonType.ground.name -> TypeUi.Ground
        PokemonType.flying.name -> TypeUi.Flying
        PokemonType.psychic.name -> TypeUi.Psychic
        PokemonType.bug.name -> TypeUi.Bug
        PokemonType.rock.name -> TypeUi.Rock
        PokemonType.ghost.name -> TypeUi.Ghost
        PokemonType.dragon.name -> TypeUi.Dragon
        PokemonType.dark.name -> TypeUi.Dark
        PokemonType.steel.name -> TypeUi.Steel
        PokemonType.fairy.name -> TypeUi.Fairy
        else -> {
            Log.e("TypeUi", "unregistered Color: $this")
            TypeUi.Normal
        }
    }
}

sealed class TypeUi(val type: PokemonType, @DrawableRes val icon: Int, val color: Color) {

    object Bug : TypeUi(type = PokemonType.bug, icon = 0, color = ColorBug)
    object Dark : TypeUi(type = PokemonType.dark, icon = 0, color = ColorDark)
    object Dragon : TypeUi(type = PokemonType.dragon, icon = 0, color = ColorDragon)
    object Electric : TypeUi(type = PokemonType.electric, icon = 0, color = ColorElectric)
    object Fairy : TypeUi(type = PokemonType.fairy, icon = 0, color = ColorFairy)
    object Fighting : TypeUi(type = PokemonType.fighting, icon = 0, color = ColorFighting)
    object Fire : TypeUi(type = PokemonType.fire, icon = 0, color = ColorFire)
    object Flying : TypeUi(type = PokemonType.flying, icon = 0, color = ColorFlying)
    object Ghost : TypeUi(type = PokemonType.ghost, icon = 0, color = ColorGhost)
    object Grass : TypeUi(type = PokemonType.grass, icon = 0, color = ColorGrass)
    object Ground : TypeUi(type = PokemonType.ground, icon = 0, color = ColorGround)
    object Ice : TypeUi(type = PokemonType.ice, icon = 0, color = ColorIce)
    object Normal : TypeUi(type = PokemonType.normal, icon = 0, color = ColorNormal)
    object Poison : TypeUi(type = PokemonType.poison, icon = 0, color = ColorPoison)
    object Psychic : TypeUi(type = PokemonType.psychic, icon = 0, color = ColorPsychic)
    object Rock : TypeUi(type = PokemonType.rock, icon = 0, color = ColorRock)
    object Steel : TypeUi(type = PokemonType.steel, icon = 0, color = ColorSteel)
    object Water : TypeUi(type = PokemonType.water, icon = 0, color = ColorWater)

}

