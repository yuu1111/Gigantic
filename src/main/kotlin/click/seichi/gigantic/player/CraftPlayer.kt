package click.seichi.gigantic.player

import click.seichi.gigantic.Gigantic
import click.seichi.gigantic.database.PlayerDao
import click.seichi.gigantic.message.messages.PlayerMessages
import click.seichi.gigantic.player.belt.Belt
import click.seichi.gigantic.player.belt.belts.AxeBelt
import click.seichi.gigantic.player.belt.belts.PickelBelt
import click.seichi.gigantic.player.belt.belts.SpadeBelt
import click.seichi.gigantic.player.components.*
import click.seichi.gigantic.player.defalutInventory.inventories.MainInventory
import click.seichi.gigantic.topbar.topbars.PlayerBars
import org.bukkit.Bukkit
import org.bukkit.boss.BossBar
import org.bukkit.entity.Player
import java.util.*

/**
 * @author tar0ss
 */
class CraftPlayer(val isFirstJoin: Boolean = false) : GiganticPlayer, RemotablePlayer {

    override val manaBar: BossBar by lazy {
        Gigantic.createInvisibleBossBar().apply {
            addPlayer(player)
        }
    }

    override val player: Player
        get() = Bukkit.getServer().getPlayer(uniqueId)

    override lateinit var uniqueId: UUID
        private set

    override lateinit var locale: Locale

    override lateinit var mana: Mana

    override lateinit var mineBlock: MineBlock

    override lateinit var memory: Memory

    override lateinit var aptitude: WillAptitude

    override val level = Level()

    override val defaultInventory = MainInventory

    override var belt: Belt = PickelBelt

    override val mineCombo = MineCombo()

    override val mineBurst = MineBurst()

    override fun switchBelt() {
        when (belt) {
            is PickelBelt -> belt = SpadeBelt
            is SpadeBelt -> belt = AxeBelt
            is AxeBelt -> belt = PickelBelt
            else -> {
            }
        }
        belt.update(player)
    }

    override fun load(playerDao: PlayerDao) {
        playerDao.user.run {
            uniqueId = id.value
            locale = Locale(localeString)
            this@CraftPlayer.mana = Mana(mana)
        }
        playerDao.userMineBlockMap.run {
            mineBlock = MineBlock(
                    map { it.key to it.value.mineBlock }
                            .toMap()
                            .toMutableMap()
            )
        }
        playerDao.userWillMap.run {
            memory = Memory(
                    map { it.key to it.value.memory }
                            .toMap().toMutableMap()
            )
            aptitude = WillAptitude(
                    filter { it.value.hasAptitude }
                            .map { it.key }
                            .toSet().toMutableSet()
            )
        }
    }

    override fun init() {
        val player = player
        if (isFirstJoin) {
            PlayerMessages.FIRST_JOIN.sendTo(player)
        }
        // レベル更新
        level.updateLevel(ExpProducer.calcExp(player)) {}
        // 表示を更新
        PlayerMessages.LEVEL_DISPLAY(level).sendTo(player)
        if (LockedFunction.MANA.isUnlocked(this)) {
            mana.updateMaxMana(level)
            val title = PlayerMessages.MANA_BAR_TITLE(mana).asSafety(locale)
            PlayerBars.MANA(mana, title).show(manaBar)
        }
        PlayerMessages.MEMORY_SIDEBAR(memory, aptitude).sendTo(player)
        // インベントリーを設定
        defaultInventory.update(player)
        // ベルトを設定
        belt.update(player)
    }

    override fun finish() {
        manaBar.removeAll()
    }

    override fun save(playerDao: PlayerDao) {
        playerDao.user.run {
            // ja_jpとなったときにjaを保存する
            localeString = locale.language.substringBefore("_")
            mana = this@CraftPlayer.mana.current
        }
        mineBlock.copyMap().forEach { reason, current ->
            playerDao.userMineBlockMap[reason]?.mineBlock = current
        }

        memory.copyMap().forEach { will, current ->
            playerDao.userWillMap[will]?.memory = current
        }

        aptitude.copySet().forEach { will ->
            playerDao.userWillMap[will]?.hasAptitude = true
        }

    }


}