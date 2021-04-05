package data.entities

import data.entities.UserEntity.uniqueIndex
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object ParticipantEntity: Table(name = "participants") {
    val id: Column<String> = varchar("id", 50).uniqueIndex()
    val registrationDate: Column<String> = varchar("registration_date", 50)
    val isPaid: Column<Boolean> = bool("is_paid")
    override val primaryKey = PrimaryKey(id, name = "pk_participants")
}