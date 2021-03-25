package data.entities

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object FinishListEntity: Table(name = "finish_lists") {
    val id: Column<String> = text("id")
    val userId: Column<String> = text("user_id")
    val competitionId: Column<String> = text("competition_id").references(CompetitionEntity.id)
    val startTime: Column<Long> = long("start_time")
    val finishTime: Column<Long> = long("finish_time")
    val resultTime: Column<Long> = long("result_time")
    val splits: Column<String> = text("splits")
    val absolutePlace: Column<Int> = integer("absolute_place")
    val placeInCategory: Column<Int> = integer("place_in_category")
    val description: Column<String> = text("description")
    override val primaryKey: PrimaryKey = PrimaryKey(id, name = "pk_finish_lists")
}