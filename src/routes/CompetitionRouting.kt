package routes

import app.Settings.COMPETITION_NEW
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
}