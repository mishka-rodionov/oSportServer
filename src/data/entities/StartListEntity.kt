package data.entities

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object StartListEntity: Table(name = "start_lists") {
    val id: Column<String> = text("id")
    val userId: Column<String> = text("user_id")
    val competitionId: Column<String> = text("competition_id").references(CompetitionEntity.id)
    val startTime: Column<Long> = long("start_time")
    val description: Column<String> = text("description")
    override val primaryKey = PrimaryKey(id, name = "pk_start_lists")
}