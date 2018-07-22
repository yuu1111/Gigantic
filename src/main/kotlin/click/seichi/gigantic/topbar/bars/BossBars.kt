package click.seichi.gigantic.topbar.bars

import click.seichi.gigantic.raid.RaidBattle
import click.seichi.gigantic.topbar.TopBar
import org.bukkit.ChatColor
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import java.math.RoundingMode

/**
 * @author tar0ss
 */
object BossBars {
    val RAID_BOSS = { battle: RaidBattle, bossName: String ->
        val boss = battle.boss
        val progress = battle.raidBoss.health.div(boss.maxHealth).coerceIn(0.0, 1.0)
        TopBar(
                "${ChatColor.RED}${ChatColor.BOLD}" +
                        "$bossName " +
                        "${battle.raidBoss.health.toBigDecimal().setScale(1, RoundingMode.UP)}" +
                        " / " +
                        "${boss.maxHealth.toBigDecimal().setScale(1, RoundingMode.UP)}",
                progress,
                BarColor.RED,
                BarStyle.SEGMENTED_12
        )
    }

}