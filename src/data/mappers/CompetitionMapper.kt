package data.mappers

import app.extensions.fromJson
import app.utils.DateFormatter
import app.utils.DateTimeFormatter
import app.utils.TimeFormatter
import com.google.gson.Gson
import data.dto.OrganizerDto
import data.dto.requests.CompetitionNewRequest
import data.dto.requests.ParticipantRequest
import data.dto.response.CompetitionShortResponse
import data.entities.CompetitionEntity
import data.entities.ParticipantEntity
import data.entities.StartListEntity
import domain.models.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import java.util.*

object CompetitionMapper {

    fun toModel(competitionNewRequest: CompetitionNewRequest) = competitionNewRequest.run {
        println(competitionNewRequest)
        val newId = UUID.randomUUID().toString()
        Competition(
            id = newId,
            date = DateTimeFormatter.parse(date),
            state = state ?: CompetitionState.CREATED,
            mainImage = mainImage,
            sportType = sportType,
            place = place,
            startInterval = startInterval,
            organizers = organizers.map {
                toOrganizerModel(it, newId)
            },
            title = title,
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
//            place = Gson().fromJson<Place>(this[CompetitionEntity.place]),
            place = Place("Saratov", Coordinate(55.44, 52.65)),
//            organizers = Gson().fromJson<List<Organizer>>(this[CompetitionEntity.organizers]),
            organizers = emptyList(),
            title = this[CompetitionEntity.title],
//            groups = ,
            startInterval = this[CompetitionEntity.startInterval],
            description = this[CompetitionEntity.description]
        )
    }

    fun toEntity(competition: Competition) = competition.run {
        CompetitionEntity.insert {
            it[id] = competition.id
            it[date] = DateTimeFormatter.format(competition.date)
            it[title] = competition.title
            it[state] = competition.state.name
            it[mainImage] = competition.mainImage.toString()
            it[sportType] = competition.sportType
            it[startInterval] = competition.startInterval
            it[organizers] = competition.organizers.toString() //TODO replace on properly formatting list of organizers
            it[place] = competition.place.toString()
            it[description] = competition.description.toString()
        }
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

    fun toStartListItem(participant: Participant, startTime: Double) = participant.run {
        StartListItem(
            userId = userId,
            competitionId = competitionId,
            startTime = startTime,
            participantGroup = group,
            description = ""
        )
    }

    fun toStartListItem(row: ResultRow) = row.run {
        StartListItem(
            row[StartListEntity.userId],
            row[StartListEntity.competitionId],
            row[StartListEntity.startTime],
            ParticipantGroup.valueOf(row[StartListEntity.participantGroup]),
            row[StartListEntity.description]
        )
    }

    fun toShortResponse(competition: Competition) = competition.run {
        CompetitionShortResponse(
            id = id,
            image = mainImage,
            title = title,
            date = DateFormatter.format(date),
            time = TimeFormatter.format(date),
            details = description ?: ""
        )
    }

}