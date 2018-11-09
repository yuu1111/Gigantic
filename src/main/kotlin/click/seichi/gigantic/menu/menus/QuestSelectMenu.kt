package click.seichi.gigantic.menu.menus

import click.seichi.gigantic.button.Button
import click.seichi.gigantic.button.buttons.menu.NextButton
import click.seichi.gigantic.button.buttons.menu.PrevButton
import click.seichi.gigantic.button.buttons.menu.QuestButtons
import click.seichi.gigantic.extension.wrappedLocale
import click.seichi.gigantic.menu.BookMenu
import click.seichi.gigantic.message.messages.menu.QuestMenuMessages
import click.seichi.gigantic.quest.Quest
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

/**
 * @author tar0ss
 */
object QuestSelectMenu : BookMenu() {

    override val size: Int
        get() = 18

    private const val numOfContentsPerPage = 9

    private val nextButton = NextButton(this)
    private val prevButton = PrevButton(this)

    override fun getMaxPage(player: Player): Int {
        return Quest.getOrderedClientList(player).size
    }

    override fun setItem(inventory: Inventory, player: Player, page: Int): Inventory {
        val clientList = Quest.getOrderedClientList(player)
        val start = (page - 1) * numOfContentsPerPage
        val end = page * numOfContentsPerPage
        (start until end)
                .filter { clientList.getOrNull(it) != null }
                .map { it % numOfContentsPerPage to clientList[it] }
                .toMap()
                .forEach { index, client ->
                    inventory.setItem(index, QuestButtons.QUEST(client).getItemStack(player))
                }
        inventory.setItem(numOfContentsPerPage + 3, prevButton.getItemStack(player))
        inventory.setItem(numOfContentsPerPage + 5, nextButton.getItemStack(player))

        return inventory
    }

    override fun getTitle(player: Player, page: Int): String {
        return "${ChatColor.BLACK}" +
                "${QuestMenuMessages.SELECT_MENU_TITLE.asSafety(player.wrappedLocale)} $page/${getMaxPage(player)}"
    }

    override fun getButton(player: Player, page: Int, slot: Int): Button? {
        val clientList = Quest.getOrderedClientList(player)
        val index = (page - 1) * numOfContentsPerPage + slot
        val client = clientList.getOrNull(index) ?: return null
        return when (slot) {
            numOfContentsPerPage + 3 -> prevButton
            numOfContentsPerPage + 5 -> nextButton
            else -> QuestButtons.QUEST(client)
        }
    }

}