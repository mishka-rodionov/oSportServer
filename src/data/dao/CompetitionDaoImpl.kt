package data.dao

import com.google.gson.Gson
import data.entities.CompetitionEntity
import data.entities.OrganizerEntity
import data.entities.ParticipantEntity
import data.models.Competition
import data.models.Organizer
import data.models.Participant
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class CompetitionDaoImpl : CompetitionDao {

    override fun setCompetition(competition: Competition) {
        transaction {
            CompetitionEntity.insert {
                it[id] = competition.id
                it[date] = competition.date.toString() //TODO replace on format date to dd.mm.yyyy hh:mm
                it[state] = competition.state.name
                it[mainImage] = competition.mainImage.toString()
                it[sportType] = competition.sportType.sportId
                it[place] = competition.place.toString()
                it[description] = competition.description.toString()
            }
        }
    }

    override fun setOrganizers(organizers: List<Organizer>) {
        organizers.forEach { organizer ->
            transaction {
                OrganizerEntity.insert {
                    it[userId] = organizer.userId
                    it[competitionId] = organizer.competitionId
                    it[organizerPosition] = organizer.position.name
                }
            }
        }
    }

    override fun setParticipants(participants: List<Participant>) {
        participants.forEach { participant ->
            transaction {
                ParticipantEntity.insert {
                    it[userId] = participant.userId
                    it[competitionId] = participant.competitionId
                    it[registrationDate] = Gson().toJson(participant.registrationDate)
                    it[gender] = participant.gender.name
                    it[group] = participant.group.name
                    it[isPaid] = participant.isPaid
                }
            }
        }
    }
}