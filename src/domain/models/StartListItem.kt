package domain.models

data class StartListItem(
//        val id: String,
        val userId: String,
        val competitionId: String,
        val startTime: Double,
        val participantGroup: ParticipantGroup,
        val description: String
)