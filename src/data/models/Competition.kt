package data.models

import java.util.*

data class Competition(
        var id: String,
        val date: Date? = null,
        val state: CompetitionState,
        val mainImage: String? = null,
        val sportType: String,
        val place: Place,
        val organizers: List<Organizer>,
//        val groups: List<ParticipantGroup>,
        val startInterval: Float,
//        val participants: List<Participant>? = null,
//        val startList: List<Participant>? = null,
//        val results: List<Participant>? = null,
        val description: String? = null
)