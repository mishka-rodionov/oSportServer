package data.repository

import data.dao.CompetitionDao
import data.dto.requests.CompetitionRequest
import data.dto.response.CompetitionShortResponse
import data.mappers.CompetitionMapper
import domain.CompetitionRepository
import domain.models.Competition
import domain.models.Participant

class CompetitionRepositoryImpl(
    private val competitionDao: CompetitionDao
) : CompetitionRepository {

    override fun createNewCompetition(newCompetition: Competition): String {
        println("start interval = ${newCompetition.startInterval}")
        competitionDao.setCompetition(newCompetition)
        competitionDao.setOrganizers(newCompetition.organizers)
        return newCompetition.id
    }

    override fun addParticipants(newParticipants: List<Participant>) {
        competitionDao.setParticipants(newParticipants)
    }

    override fun generateStartLists(competitionId: String) {
        val startInterval = competitionDao.getCompetition(competitionId).startInterval
        val listOfParticipants = competitionDao.getParticipants(competitionId)
        val setOfParticipantGroup = listOfParticipants.map { it.group }.toHashSet()
        setOfParticipantGroup.forEach { group ->
            val currentGroup = listOfParticipants.filter { it.group == group }
            competitionDao.setStartList(currentGroup.shuffled().mapIndexed { index, participant ->
                CompetitionMapper.toStartListItem(participant = participant, startTime = index * startInterval)
            }
            )
        }
    }

    override fun getParticipants(competitionId: String) = competitionDao.getParticipants(competitionId)

    override fun getStartList(competitionId: String) = competitionDao.getStartList(competitionId)

    override suspend fun getCompetitions(competitionRequest: CompetitionRequest): List<CompetitionShortResponse> {
        return competitionDao.getCompetitions(competitionRequest.offset, competitionRequest.limit).map(CompetitionMapper::toShortResponse)
    }
}