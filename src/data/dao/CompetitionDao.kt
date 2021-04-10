package data.dao

import data.models.Competition
import data.models.Organizer
import data.models.Participant
import data.models.StartListItem

interface CompetitionDao {

    fun setCompetition(competition: Competition)
    fun getCompetition(competitionId: String): Competition

    fun setOrganizers(organizers: List<Organizer>)

    fun setParticipants(participants: List<Participant>)
    fun getParticipants(competitionId: String): List<Participant>

    fun setStartList(startList: List<StartListItem>)
}