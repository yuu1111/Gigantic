package click.seichi.gigantic.acheivement

import click.seichi.gigantic.belt.Belt
import click.seichi.gigantic.cache.key.Keys
import click.seichi.gigantic.cache.manipulator.catalog.CatalogPlayerCache
import click.seichi.gigantic.extension.*
import click.seichi.gigantic.message.ChatMessage
import click.seichi.gigantic.message.messages.AchievementMessages
import click.seichi.gigantic.message.messages.SideBarMessages
import click.seichi.gigantic.quest.Quest
import click.seichi.gigantic.tool.Tool
import click.seichi.gigantic.will.Will
import org.bukkit.entity.Player

/**
 * @author tar0ss
 *
 *
 */
enum class Achievement(
        val id: Int,
        private val canGranting: (Player) -> Boolean,
        // 毎Login時とアンロック時に処理される
        val action: (Player) -> Unit = {},
        val grantMessage: ChatMessage? = null,
        private val priority: UpdatePriority = UpdatePriority.NORMAL
) {
    // messages
    JOIN_SERVER(0, { true }, action = { player ->
        Tool.PICKEL.grant(player)
        Tool.SHOVEL.grant(player)
        Tool.AXE.grant(player)
        Belt.DEFAULT.grant(player)
    }, grantMessage = AchievementMessages.FIRST_JOIN),
    FIRST_LEVEL_UP(1, {
        it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 2
    }, grantMessage = AchievementMessages.FIRST_LEVEL_UP),

    MINE_COMBO(2, {
        it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 3
    }, grantMessage = AchievementMessages.MINE_COMBO),


    // systems
    MANA_STONE(100, {
        it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 10
    }, grantMessage = AchievementMessages.MANA_STONE,
            priority = UpdatePriority.HIGHEST),
    TELEPORT_PLAYER(101, {
        it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 4
    }, grantMessage = AchievementMessages.TELEPORT_PLAYER),
    QUEST(102, {
        it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 5
    }, priority = UpdatePriority.HIGHEST),
    TELEPORT_LAST_DEATH(103, {
        it.find(CatalogPlayerCache.LEVEL)?.current ?: 0 >= 6
    }, grantMessage = AchievementMessages.TELEPORT_LAST_DEATH),

    // skills
    SKILL_FLASH(200, {
        Quest.PIG.isCleared(it)
    }, grantMessage = AchievementMessages.UNLOCK_FLASH),
    SKILL_MINE_BURST(201, {
        Quest.BLAZE.isCleared(it)
    }, grantMessage = AchievementMessages.SKILL_MINE_BURST),

    // spells
    SPELL_STELLA_CLAIR(300, {
        MANA_STONE.isGranted(it)
    }, grantMessage = AchievementMessages.UNLOCK_STELLA_CLAIR),

    // quest order
    QUEST_LADON_ORDER(400, {
        false
    }, action = {
        Quest.LADON.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_UNDINE_ORDER(401, {
        MANA_STONE.isGranted(it) &&
                Will.AQUA.isAptitude(it)
    }, action = {
        Quest.UNDINE.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_SALAMANDRA_ORDER(402, {
        MANA_STONE.isGranted(it) &&
                Will.IGNIS.isAptitude(it)
    }, action = {
        Quest.SALAMANDRA.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_SYLPHID_ORDER(403, {
        MANA_STONE.isGranted(it) &&
                Will.AER.isAptitude(it)
    }, action = {
        Quest.SYLPHID.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_NOMOS_ORDER(404, {
        MANA_STONE.isGranted(it) &&
                Will.TERRA.isAptitude(it)
    }, action = {
        Quest.NOMOS.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_LOA_ORDER(405, {
        MANA_STONE.isGranted(it) &&
                Will.NATURA.isAptitude(it)
    }, action = {
        Quest.LOA.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_PIG_ORDER(406, {
        Quest.BEGIN.isCleared(it)
    }, action = {
        Quest.PIG.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_BLAZE_ORDER(407, {
        Quest.PIG.isCleared(it)
    }, action = {
        Quest.BLAZE.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_CHICKEN_ORDER(408, {
        false
    }, action = {
        Quest.CHICKEN.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_WITHER_ORDER(409, {
        false
    }, action = {
        Quest.WITHER.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_BEGINS_ORDER(410, {
        QUEST.isGranted(it)
    }, action = {
        Quest.BEGIN.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER_FIRST),
    QUEST_TURTLE_ORDER(411, {
        false
    }, action = {
        Quest.TURTLE.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_SPIDER_ORDER(412, {
        false
    }, action = {
        Quest.SPIDER.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_ZOMBIE_ORDER(413, {
        false
    }, action = {
        Quest.ZOMBIE.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_SKELETON_ORDER(414, {
        false
    }, action = {
        Quest.SKELETON.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_ORC_ORDER(415, {
        false
    }, action = {
        Quest.ORC.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_GHOST_ORDER(416, {
        false
    }, action = {
        Quest.GHOST.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_PARROT_ORDER(417, {
        false
    }, action = {
        Quest.PARROT.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_SLIME_ORDER(418, {
        false
    }, action = {
        Quest.SLIME.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    QUEST_ENDER_MAN_ORDER(419, {
        false
    }, action = {
        Quest.ENDER_MAN.order(it)
    }, grantMessage = AchievementMessages.QUEST_ORDER),
    ;

    /**1から順に [update] される**/
    enum class UpdatePriority(val amount: Int) {
        HIGHEST(1), HIGH(2), NORMAL(3), LOW(4), LOWEST(5)
    }

    companion object {
        fun update(player: Player) {
            values().sortedBy { it -> it.priority.amount }
                    .forEach {
                        it.update(player)
                    }
            player.updateInventory(true, true)
            SideBarMessages.MEMORY_SIDEBAR(
                    player.find(CatalogPlayerCache.MEMORY) ?: return,
                    player.find(CatalogPlayerCache.APTITUDE) ?: return,
                    true
            ).sendTo(player)
        }
    }


    private fun update(player: Player) {
        if (canGrant(player)) {
            if (!isGranted(player)) {
                // 現在も解除可能で解除していない時
                grant(player)
            }
        } else {
            if (isGranted(player)) {
                // 現在解除できないがすでに解除しているとき
                revoke(player)
            }
        }
    }

    // 与える
    private fun grant(player: Player) {
        // 解除処理
        player.transform(Keys.ACHIEVEMENT_MAP[this] ?: return) { hasUnlocked ->
            if (!hasUnlocked) {
                action(player)
                grantMessage?.sendTo(player)
            }
            true
        }
    }

    // はく奪する
    private fun revoke(player: Player) {
        // ロック処理
        player.transform(Keys.ACHIEVEMENT_MAP[this] ?: return) {
            false
        }
    }

    private fun canGrant(player: Player) = canGranting(player)

    fun isGranted(player: Player) = canGrant(player) && Keys.ACHIEVEMENT_MAP[this]?.let { player.getOrPut(it) } ?: false
}