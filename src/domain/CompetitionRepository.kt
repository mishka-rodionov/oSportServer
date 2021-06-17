package domain

import domain.models.Competition
import domain.models.Participant
import domain.models.StartListItem

interface CompetitionRepository {

    fun createNewCompetition(newCompetition: Competition) : String
    fun addParticipants(newParticipants: List<Participant>)
    fun generateStartLists(competitionId: String)
    fun getParticipants(competitionId: String): List<Participant>
    fun getStartList(competitionId: String): List<StartListItem>

}