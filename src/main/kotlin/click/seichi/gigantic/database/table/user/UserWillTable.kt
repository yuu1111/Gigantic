package click.seichi.gigantic.database.table.user

import org.jetbrains.exposed.dao.IntIdTable

/**
 * @author tar0ss
 */
object UserWillTable : IntIdTable("users_wills") {

    val userId = reference("unique_id", UserTable).primaryKey()

    val willId = integer("will_id").primaryKey()

    val ethel = long("ethel").default(0L)

    val hasAptitude = bool("has_aptitude").default(false)

    val secretAmount = long("secret_amount").default(0L)

}