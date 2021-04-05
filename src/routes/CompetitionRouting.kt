package routes

import app.Settings.COMPETITION_NEW
import app.Settings.PARTICIPANT_NEW
import data.dto.requests.ParticipantRequest
import data.mappers.CommonMapper
import data.mappers.CompetitionMapper
import domain.CompetitionRepository
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post

fun Routing.competitions(competitionRepository: CompetitionRepository) {
    post(COMPETITION_NEW) {
        call.respond(
            CommonMapper.toIdDto(
                id = competitionRepository.createNewCompetition(
                    CompetitionMapper.toModel(
                        competitionRequest = call.receive()
                    )
                )
            )
        )
    }

    post(PARTICIPANT_NEW) {
        competitionRepository.addParticipants(
            (call.receive() as List<ParticipantRequest>).map(CompetitionMapper::toParticipantModel)
        )
    }
}