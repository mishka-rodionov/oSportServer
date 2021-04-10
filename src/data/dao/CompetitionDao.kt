package data.dao

import data.models.Competition
import data.models.Organizer
import data.models.Participant

interface CompetitionDao {

    fun setCompetition(competition: Competition)
    fun setOrganizers(organizers: List<Organizer>)
    fun setParticipants(participants: List<Participant>)
    fun getParticipants(competitionId: String): List<Participant>

}