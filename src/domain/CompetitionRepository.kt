package domain

import data.models.Competition
import data.models.Participant

interface CompetitionRepository {

    fun createNewCompetition(newCompetition: Competition) : String
    fun addParticipants(newParticipants: List<Participant>)
    fun generateStartLists(competitionId: String)
    fun getParticipants(competitionId: String): List<Participant>
    fun getStartList(competitionId: String)

}