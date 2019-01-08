package click.seichi.gigantic.message.messages

import click.seichi.gigantic.extension.hasAptitude
import click.seichi.gigantic.extension.memory
import click.seichi.gigantic.message.LocalizedText
import click.seichi.gigantic.message.SideBarMessage
import click.seichi.gigantic.util.SideBarRow
import click.seichi.gigantic.will.Will
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.*

/**
 * @author tar0ss
 */
object SideBarMessages {

    val MEMORY_SIDEBAR = { player: Player, isForced: Boolean ->
        val willMap = Will.values()
                .filter { player.hasAptitude(it) }
                .map { it to player.memory(it) }.toMap()
        SideBarMessage(
                "memory",
                LocalizedText(
                        Locale.JAPANESE to "${ChatColor.GREEN}${ChatColor.BOLD}" +
                                "遺志の記憶"
                ),
                willMap.keys.filter {
                    willMap[it]!! > 0
                }.map { will ->
                    SideBarRow.getRowById(will.id) to LocalizedText(
                            Locale.JAPANESE.let { locale ->
                                locale to "${will.chatColor}${ChatColor.BOLD}" +
                                        "${will.getName(locale).let {
                                            if (it.length == 2) it
                                            else " $it "
                                        }}:" +
                                        "${ChatColor.RESET}" +
                                        "${will.chatColor}" +
                                        "${willMap[will]!!.coerceAtMost(9999)}".padStart(4, ' ')
                            }
                    )
                }.toMap()
                , isForced
        )
    }
}