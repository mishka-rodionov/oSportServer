package data.dao

import com.google.gson.Gson
import data.entities.CompetitionEntity
import data.entities.OrganizerEntity
import data.entities.ParticipantEntity
import data.entities.StartListEntity
import data.mappers.CompetitionMapper
import domain.models.Competition
import domain.models.Organizer
import domain.models.Participant
import domain.models.StartListItem
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class CompetitionDaoImpl : CompetitionDao {

    override fun setCompetition(competition: Competition) {
        transaction {
            CompetitionEntity.insert {
                it[id] = competition.id
                it[date] = competition.date.toString() //TODO replace on format date to dd.mm.yyyy hh:mm
                it[title] = competition.title
                it[state] = competition.state.name
                it[mainImage] = competition.mainImage.toString()
                it[sportType] = competition.sportType
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

    override fun getParticipants(competitionId: String): List<Participant> {
        return transaction {
            ParticipantEntity.select { ParticipantEntity.competitionId eq competitionId }
        }.map(CompetitionMapper::toParticipantModel)
    }

    override fun getCompetition(competitionId: String): Competition {
        return transaction {
            CompetitionEntity.select { CompetitionEntity.id eq competitionId }
        }.map(CompetitionMapper::toModel).first()
    }

    override fun setStartList(startList: List<StartListItem>) {
        startList.forEach { startListItem ->
            transaction {
                StartListEntity.insert {
                    it[userId] = startListItem.userId
                    it[competitionId] = startListItem.competitionId
                    it[startTime] = startListItem.startTime
                    it[description] = startListItem.description
                    it[participantGroup] = startListItem.participantGroup.name
                }
            }
        }
    }

    override fun getStartList(competitionId: String): List<StartListItem> {
        return transaction {
            StartListEntity.select { StartListEntity.competitionId eq competitionId }.map(CompetitionMapper::toStartListItem)
        }
    }
}