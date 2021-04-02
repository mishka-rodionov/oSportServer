package data.mappers

import data.dto.requests.CompetitionRequest
import data.models.Competition
import data.models.CompetitionState
import java.util.*

object CompetitionMapper {

    fun toModel(competitionRequest: CompetitionRequest) = competitionRequest.run {
        println(competitionRequest)
        Competition(
            id = "dummy",
            Date(), state ?: CompetitionState.CREATED, mainImage, sportType, place, organizers, description
        )
    }

}