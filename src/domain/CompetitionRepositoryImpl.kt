package domain

import data.dao.CompetitionDao
import data.models.Competition
import data.models.Participant

class CompetitionRepositoryImpl(
    private val competitionDao: CompetitionDao
) : CompetitionRepository {

    override fun createNewCompetition(newCompetition: Competition): String {
        competitionDao.setCompetition(newCompetition)
        competitionDao.setOrganizers(newCompetition.organizers)
        return newCompetition.id
    }

    override fun addParticipants(newParticipants: List<Participant>) {
        competitionDao.setParticipants(newParticipants)
    }

    override fun generateStartLists(competitionId: String) {
        val listOfParticipants = competitionDao.getParticipants(competitionId)
        val setOfParticipantGroup = listOfParticipants.map { it.group }.toHashSet()
        setOfParticipantGroup.forEach { group ->
            val currentGroup = listOfParticipants.filter { it.group == group }
            currentGroup.shuffled()
        }
    }
}