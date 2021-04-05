package data.mappers

import app.utils.DateTimeFormatter
import data.dto.OrganizerDto
import data.dto.requests.CompetitionRequest
import data.dto.requests.ParticipantRequest
import data.models.*
import java.text.SimpleDateFormat
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

    private fun toOrganizerModel(organizerDto: OrganizerDto, competitionId: String) = organizerDto.run {
        Organizer(
            userId, competitionId, OrganizersPosition.valueOf(organizerPosition)
        )
    }

    fun toParticipantModel(participantRequest: ParticipantRequest) = participantRequest.run {
        Participant(
            userId, competitionId, DateTimeFormatter.parse(registrationDate), Gender.valueOf(gender), ParticipantGroup.valueOf(group), isPaid
        )
    }

}