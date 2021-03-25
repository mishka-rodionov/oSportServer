package data.models

data class Organizer(
        val userId: String,
        val competitionId: String,
        val position: OrganizersPosition = OrganizersPosition.REFEREE
        )