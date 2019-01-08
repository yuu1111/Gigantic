package click.seichi.gigantic.animation.animations

import click.seichi.gigantic.animation.Animation
import click.seichi.gigantic.extension.spawnColoredParticle
import click.seichi.gigantic.extension.spawnColoredParticleSpherically
import click.seichi.gigantic.util.NoiseData
import click.seichi.gigantic.will.WillRenderingData
import org.bukkit.Color

/**
 * @author tar0ss
 */
object WillSpiritAnimations {

    val RENDER = { renderingData: WillRenderingData, color: Color, lifeExpectancy: Long ->
        Animation(0) { location, ticks ->
            location.world.spawnColoredParticleSpherically(
                    location,
                    color,
                    if (lifeExpectancy < 10 * 20 && (renderingData.beatTiming == 0 || lifeExpectancy % renderingData.beatTiming == 0L)) {
                        renderingData.min
                    } else {
                        renderingData.max
                    },
                    renderingData.radius
            )
        }
    }

    val SENSE = { color: Color ->
        Animation(0) { location, _ ->
            location.world.spawnColoredParticle(
                    location,
                    color,
                    noiseData = NoiseData(0.01)
            )
        }

    }

}