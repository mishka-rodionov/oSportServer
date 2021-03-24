package data.entities

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object CompetitionEntity: Table(name = "competitions") {
    val id: Column<String> = text("id")
    val date: Column<String> = text("date")
    val state: Column<String> = text("state")
    val mainImage: Column<String> = text("main_image")
    val sportType: Column<String> = text("sport_type")
    val place: Column<String> = text("place")
    val organizers: Column<String> = text("organizers")
    val participants: Column<String> = text("participants")
    val startList: Column<String> = text("start_list")
    val results: Column<String> = text("results")
    val description: Column<String> = text("description")
    override val primaryKey = PrimaryKey(id, name = "pk_competitions")
}