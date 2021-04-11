package domain

import data.models.Competition
import data.models.Participant
import data.models.ParticipantGroup
import data.models.StartListItem

interface CompetitionRepository {

    fun createNewCompetition(newCompetition: Competition) : String
    fun addParticipants(newParticipants: List<Participant>)
    fun generateStartLists(competitionId: String)
    fun getParticipants(competitionId: String): List<Participant>
    fun getStartList(competitionId: String, participantGroup: ParticipantGroup): List<StartListItem>

}