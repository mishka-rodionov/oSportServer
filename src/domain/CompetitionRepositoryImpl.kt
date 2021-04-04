package domain

import data.dao.CompetitionDao
import data.models.Competition
import java.util.*

class CompetitionRepositoryImpl(
    private val competitionDao: CompetitionDao
): CompetitionRepository {

    override fun createNewCompetition(newCompetition: Competition) : String {
//        newCompetition.id = UUID.randomUUID().toString()
        competitionDao.setCompetition(newCompetition)
        competitionDao.setOrganizers(newCompetition.organizers)
        return newCompetition.id
    }
}