package domain.models

import java.util.*

data class Competition(
        var id: String,
        val date: Date? = null,
        val state: CompetitionState,
        val mainImage: String? = null,
        val sportType: String,
        val place: Place,
        val organizers: List<Organizer>,
        val title: String,
//        val groups: List<ParticipantGroup>,
        val startInterval: Double,
//        val participants: List<Participant>? = null,
//        val startList: List<Participant>? = null,
//        val results: List<Participant>? = null,
        val description: String? = null
)