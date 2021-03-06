package click.seichi.gigantic.listener

import click.seichi.gigantic.extension.summonSpirit
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

/**
 * @unicroak
 * @author tar0ss
 */
class SpiritListener : Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player
        if (player.gameMode != GameMode.SURVIVAL) return
        event.summonSpirit()
    }

}