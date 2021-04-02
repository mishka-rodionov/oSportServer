package data.dao

import data.entities.CompetitionEntity
import data.models.Competition
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class CompetitionDaoImpl: CompetitionDao {

    override fun setCompetition(competition: Competition) {
        transaction {
            CompetitionEntity.insert {
                it[id] = competition.id
                it[date] = competition.date.toString() //TODO replace on format date to dd.mm.yyyy hh:mm
                it[state] = competition.state.name
                it[mainImage] = competition.mainImage.toString()
                it[sportType] = competition.sportType.sportId
                it[place] = competition.place.toString()
//                it[organizers] = competition.organizers
//                it[participants] = competition.participants
//                it[startList] = competition.startList
//                it[results] = competition.results
                it[description] = competition.description.toString()
            }
        }
    }
}