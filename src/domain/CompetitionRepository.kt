package domain

import data.models.Competition

interface CompetitionRepository {

    fun createNewCompetition(newCompetition: Competition) : String

}