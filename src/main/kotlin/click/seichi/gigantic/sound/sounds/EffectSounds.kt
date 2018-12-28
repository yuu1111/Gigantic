package click.seichi.gigantic.sound.sounds

import click.seichi.gigantic.sound.DetailedSound
import org.bukkit.Sound
import org.bukkit.SoundCategory

/**
 * @author tar0ss
 */
object EffectSounds {

    val EXPLOSION = DetailedSound(
            Sound.ENTITY_GENERIC_EXPLODE,
            SoundCategory.BLOCKS,
            0.1F,
            1.0F
    )

    val BLIZZARD = DetailedSound(
            Sound.BLOCK_GLASS_BREAK,
            SoundCategory.BLOCKS,
            0.1F,
            1.0F
    )

    val MAGIC = DetailedSound(
            Sound.ENTITY_CHICKEN_EGG,
            SoundCategory.BLOCKS,
            0.4F,
            1.5F
    )
}