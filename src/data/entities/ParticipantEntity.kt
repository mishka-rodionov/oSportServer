package data.entities

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object ParticipantEntity: Table(name = "participants") {
    val userId: Column<String> = varchar("user_id", 50)
    val competitionId: Column<String> = varchar("competition_id", 50)
    val registrationDate: Column<String> = varchar("registration_date", 50)
    val isPaid: Column<Boolean?> = bool("is_paid").nullable()
    override val primaryKey = PrimaryKey(userId, competitionId, name = "pk_participants")
}