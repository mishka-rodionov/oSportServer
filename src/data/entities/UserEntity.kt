package data.entities

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object UserEntity : Table(name = "users") {
    val id: Column<String> = text("id").uniqueIndex()
    val firstName: Column<String> = text("first_name")
    val middleName: Column<String> = text("middle_name")
    val lastName: Column<String> = text("last_name")
    val phoneCountryPrefix: Column<String> = text("phone_country_prefix")
    val phoneNumber: Column<String> = text("phone_number")
    val email: Column<String> = text("email")
    val birthDate: Column<String> = text("birth_date")
    val sportRanks: Column<String> = text("sport_ranks")
    override val primaryKey = PrimaryKey(id, name = "pk_users")
}
