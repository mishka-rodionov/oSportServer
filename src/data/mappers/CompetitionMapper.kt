package data.mappers

import data.dto.OrganizerDto
import data.dto.requests.CompetitionRequest
import data.models.Competition
import data.models.CompetitionState
import data.models.Organizer
import data.models.OrganizersPosition
import java.util.*

object CompetitionMapper {

    fun toModel(competitionRequest: CompetitionRequest) = competitionRequest.run {
        println(competitionRequest)
        val newId = UUID.randomUUID().toString()
        Competition(
            id = newId,
            Date(), state ?: CompetitionState.CREATED, mainImage, sportType, place, organizers.map{
                toOrganizerModel(it, newId)
            }, description
        )
    }

    fun toOrganizerModel(organizerDto: OrganizerDto, competitionId: String) = organizerDto.run {
        Organizer(
            userId, competitionId, OrganizersPosition.valueOf(organizerPosition)
        )
    }

}