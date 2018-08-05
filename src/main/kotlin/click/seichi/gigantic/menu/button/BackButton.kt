package click.seichi.gigantic.menu.button

import click.seichi.gigantic.button.Button
import click.seichi.gigantic.head.Head
import click.seichi.gigantic.menu.Menu
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * @author tar0ss
 */
class BackButton(private val currentMenu: Menu, private val menu: Menu) : Button {

    override fun getItemStack(player: Player): ItemStack? {
        return Head.LEFT.toItemStack().apply {
            val title = menu.getTitle(player)
//            setDisplayName(
//                    MenuMessages.BACK_BUTTON(title).asSafety(player.wrappedLocale)
//            )
        }
    }

    override fun onClick(player: Player, event: InventoryClickEvent) {
        currentMenu.back(menu, player)
    }

}