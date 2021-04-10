package data.mappers

import app.fromJson
import app.utils.DateTimeFormatter
import com.google.gson.Gson
import data.dto.OrganizerDto
import data.dto.requests.CompetitionRequest
import data.dto.requests.ParticipantRequest
import data.entities.CompetitionEntity
import data.entities.ParticipantEntity
import data.models.*
import org.jetbrains.exposed.sql.ResultRow
import java.text.SimpleDateFormat
import java.util.*

object CompetitionMapper {

    fun toModel(competitionRequest: CompetitionRequest) = competitionRequest.run {
        println(competitionRequest)
        val newId = UUID.randomUUID().toString()
        Competition(
            id = newId,
            date = Date(),
            state = state ?: CompetitionState.CREATED,
            mainImage = mainImage,
            sportType = sportType,
            place = place,
            startInterval = startInterval,
            organizers = organizers.map {
                toOrganizerModel(it, newId)
            },
//            groups = participantGroups.map {
//                ParticipantGroup.valueOf(it)
//            },
            description = description
        )
    }

    fun toModel(row: ResultRow) = row.run {
        Competition(
            id = this[CompetitionEntity.id],
            date = DateTimeFormatter.parse(this[CompetitionEntity.date]),
            state = CompetitionState.valueOf(this[CompetitionEntity.state]),
            mainImage = this[CompetitionEntity.mainImage],
            sportType = this[CompetitionEntity.sportType],
            place = Gson().fromJson<Place>(this[CompetitionEntity.place]),
            organizers = Gson().fromJson<List<Organizer>>(this[CompetitionEntity.organizers]),
//            groups = ,
            startInterval = this[CompetitionEntity.startInterval],
            description = this[CompetitionEntity.description]
        )
    }

    private fun toOrganizerModel(organizerDto: OrganizerDto, competitionId: String) = organizerDto.run {
        Organizer(
            userId, competitionId, OrganizersPosition.valueOf(organizerPosition)
        )
    }

    fun toParticipantModel(participantRequest: ParticipantRequest) = participantRequest.run {
        Participant(
            userId,
            competitionId,
            DateTimeFormatter.parse(registrationDate),
            Gender.valueOf(gender),
            ParticipantGroup.valueOf(group),
            isPaid
        )
    }

    fun toParticipantModel(row: ResultRow) = row.run {
        Participant(
            row[ParticipantEntity.userId],
            row[ParticipantEntity.competitionId],
            DateTimeFormatter.parse(row[ParticipantEntity.registrationDate]),
            Gender.valueOf(row[ParticipantEntity.gender]),
            ParticipantGroup.valueOf(row[ParticipantEntity.group]),
            row[ParticipantEntity.isPaid]
        )
    }

    fun toStartListItem(participant: Participant, startTime: Float) = participant.run {
        StartListItem(
            userId = userId,
            competitionId = competitionId,
            startTime = startTime,
            participantGroup = group,
            description = ""
        )
    }

}