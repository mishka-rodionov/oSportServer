package data.dao

import data.models.Competition
import data.models.Organizer

interface CompetitionDao {

    fun setCompetition(competition: Competition)
    fun setOrganizers(organizers: List<Organizer>)

}