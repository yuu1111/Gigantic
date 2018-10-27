package click.seichi.gigantic.player.spell

import org.bukkit.Material

/**
 * @author tar0ss
 */
object SpellParameters {

    const val STELLA_CLAIR_AMOUNT_PERCENT = 7

    const val STELLA_CLAIR_PROBABILITY_PERCENT = 10

    const val TERRA_DRAIN_LOG_HEAL_PERCENT = 3.0

    const val TERRA_DRAIN_LEAVES_HEAL_PERCENT = 0.3

    const val TERRA_DRAIN_MANA = 8L

    const val IGNIS_VOLCANO_MAX_RADIUS = 7

    const val IGNIS_VOLCANO_MANA = 28L

    val IGNIS_VOLCANO_RELATIONAL_BLOCKS = setOf(
            Material.GRASS_BLOCK,
            Material.BROWN_MUSHROOM_BLOCK,
            Material.RED_MUSHROOM_BLOCK,
            Material.MUSHROOM_STEM
    )

}