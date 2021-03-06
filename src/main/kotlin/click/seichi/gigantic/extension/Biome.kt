package click.seichi.gigantic.extension

import click.seichi.gigantic.message.LocalizedText
import click.seichi.gigantic.message.messages.BiomeMessages
import click.seichi.gigantic.util.BiomeGroup
import org.bukkit.block.Biome

/**
 * @author tar0ss
 */

val RIVERS = setOf(
        Biome.RIVER,
        Biome.FROZEN_RIVER
)

val MUSHROOMS = setOf(
        Biome.MUSHROOM_FIELDS,
        Biome.MUSHROOM_FIELD_SHORE
)

val MOUNTAINS = setOf(
        Biome.MOUNTAINS,
        Biome.GRAVELLY_MOUNTAINS,
        Biome.WOODED_MOUNTAINS,
        Biome.MODIFIED_GRAVELLY_MOUNTAINS,
        Biome.STONE_SHORE,
        Biome.SNOWY_MOUNTAINS,
        Biome.SNOWY_TAIGA_MOUNTAINS,
        Biome.TAIGA_MOUNTAINS
)

val FORESTS = setOf(
        Biome.FOREST,
        Biome.DARK_FOREST_HILLS,
        Biome.DARK_FOREST,
        Biome.BIRCH_FOREST,
        Biome.BIRCH_FOREST_HILLS,
        Biome.FLOWER_FOREST,
        Biome.TALL_BIRCH_FOREST,
        Biome.JUNGLE,
        Biome.JUNGLE_EDGE,
        Biome.JUNGLE_HILLS,
        Biome.MODIFIED_JUNGLE_EDGE,
        Biome.MODIFIED_JUNGLE
)

val HILLS = setOf(
        Biome.JUNGLE_HILLS,
        Biome.BIRCH_FOREST_HILLS,
        Biome.DARK_FOREST_HILLS,
        Biome.DESERT_HILLS,
        Biome.GIANT_SPRUCE_TAIGA_HILLS,
        Biome.GIANT_TREE_TAIGA_HILLS,
        Biome.SNOWY_TAIGA_HILLS,
        Biome.SWAMP_HILLS,
        Biome.TAIGA_HILLS,
        Biome.TALL_BIRCH_HILLS,
        Biome.WOODED_HILLS
)

val Biome.isOcean: Boolean
    get() = BiomeGroup.OCEAN.isBelongTo(this)

val Biome.isRiver: Boolean
    get() = RIVERS.contains(this)

val Biome.isMushRoom: Boolean
    get() = MUSHROOMS.contains(this)

val Biome.isMountain: Boolean
    get() = MOUNTAINS.contains(this)

val Biome.isForest: Boolean
    get() = FORESTS.contains(this)

val Biome.isHill: Boolean
    get() = HILLS.contains(this)

val Biome.isSnowy: Boolean
    get() = BiomeGroup.SNOWY.isBelongTo(this)

val Biome.isCold: Boolean
    get() = BiomeGroup.COLD.isBelongTo(this)

val Biome.isWormed: Boolean
    get() = BiomeGroup.WORM.isBelongTo(this)

val Biome.isDried: Boolean
    get() = BiomeGroup.DRY.isBelongTo(this)

val Biome.localizedName: LocalizedText
    get() = BiomeMessages.BIOME(this)
