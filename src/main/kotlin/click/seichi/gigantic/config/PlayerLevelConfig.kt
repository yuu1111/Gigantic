package click.seichi.gigantic.config

import click.seichi.gigantic.Gigantic

/**
 * @author tar0ss
 */
object PlayerLevelConfig : SimpleConfiguration("level", Gigantic.PLUGIN) {

    val MAX by lazy { getInt("max") }

    val LEVEL_MAP by lazy {
        (1..(MAX + 1)).map {
            it to getLong("level_to_exp.$it")
        }.toMap()
    }

}