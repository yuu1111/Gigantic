package click.seichi.gigantic.database.dao.ranking

import click.seichi.gigantic.database.table.ranking.RankingScoreTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import java.util.*

/**
 * @author tar0ss
 */
class RankingScore(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, RankingScore>(RankingScoreTable)

    var exp by RankingScoreTable.exp

    var breakBlock by RankingScoreTable.breakBlock

    var multiBreakBlock by RankingScoreTable.multiBreakBlock

    var relicBonus by RankingScoreTable.relicBonus

    var maxCombo by RankingScoreTable.maxCombo

    var relic by RankingScoreTable.relic

    var stripMine by RankingScoreTable.stripMine

}