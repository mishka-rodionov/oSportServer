package data.entities

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object OrganizerEntity: Table(name = "organizers") {
    val id: Column<String> = text("id")
    val userId: Column<String> = text("user_id")
    val competitionId: Column<String> = text("competition_id").references(CompetitionEntity.id)
    val organizerPosition: Column<String> = text("organizer_position")
    override val primaryKey = PrimaryKey(id, name = "pk_competitions")
}