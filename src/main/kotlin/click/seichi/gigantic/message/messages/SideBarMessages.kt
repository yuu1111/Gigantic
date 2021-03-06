package click.seichi.gigantic.message.messages

import click.seichi.gigantic.GiganticEvent
import click.seichi.gigantic.extension.ethel
import click.seichi.gigantic.extension.hasAptitude
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

    val ETHEL = { player: Player ->

        val willMap = Will.values()
                .filter { player.hasAptitude(it) }
                .map { it to player.ethel(it) }.toMap()
        SideBarMessage(
                "ethel",
                LocalizedText(
                        Locale.JAPANESE to "${ChatColor.GREEN}${ChatColor.BOLD}" +
                                "エーテル"
                ),
                // 意志の設定
                willMap.keys.filter {
                    willMap.getValue(it) > 0
                }.filter {
                    // TODO ここはこの実装だと新意志実装時に追記し忘れる可能性あり
                    // イベントは特殊な形でフィルター
                    when (it) {
                        Will.SAKURA -> GiganticEvent.SAKURA.isActive()
                        Will.MIO -> GiganticEvent.MIO.isActive()
                        else -> true
                    }
                }.map { will ->
                    val row = when (will) {
                        Will.AQUA -> SideBarRow.TWO
                        Will.IGNIS -> SideBarRow.THREE
                        Will.AER -> SideBarRow.FIVE
                        Will.TERRA -> SideBarRow.ONE
                        Will.NATURA -> SideBarRow.FOUR
                        Will.GLACIES -> SideBarRow.SIX
                        Will.LUX -> SideBarRow.NINE
                        Will.SOLUM -> SideBarRow.SEVEN
                        Will.UMBRA -> SideBarRow.TEN
                        Will.VENTUS -> SideBarRow.EIGHT
                        else -> SideBarRow.FOURTEEN
                    }
                    row to LocalizedText(
                            Locale.JAPANESE.let { locale ->
                                locale to "${will.chatColor}${ChatColor.BOLD}" +
                                        "${will.getName(locale).let {
                                            if (it.length == 2) it
                                            else " $it "
                                        }}:" +
                                        "${ChatColor.RESET}" +
                                        "${will.chatColor}" +
                                        "${willMap.getValue(will).coerceAtMost(999)}".padStart(4, ' ')
                            }
                    )
                }.toMap()
        )
    }
}