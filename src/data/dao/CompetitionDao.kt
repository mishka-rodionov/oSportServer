package data.dao

import domain.models.Competition
import domain.models.Organizer
import domain.models.Participant
import domain.models.StartListItem

interface CompetitionDao {

    fun setCompetition(competition: Competition)
    fun getCompetition(competitionId: String): Competition

    fun setOrganizers(organizers: List<Organizer>)

    fun setParticipants(participants: List<Participant>)
    fun getParticipants(competitionId: String): List<Participant>

    fun setStartList(startList: List<StartListItem>)
    fun getStartList(competitionId: String): List<StartListItem>

    suspend fun getCompetitions(skip: Int, limit: Int): List<Competition>
}