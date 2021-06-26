package data.entities

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object StartListEntity: Table(name = "start_lists") {
//    val id: Column<String> = text("id")
    val userId: Column<String> = text("user_id")
    val competitionId: Column<String> = text("competition_id").references(ref = CompetitionEntity.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val startTime: Column<Double> = double("start_time")
    val participantGroup: Column<String> = varchar("participant_group", 20)
    val description: Column<String> = text("description")
    override val primaryKey = PrimaryKey(userId, competitionId, name = "pk_start_lists")
}