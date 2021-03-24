package data.models

data class StartListItem(
        val id: String,
        val userId: String,
        val competitionId: String,
        val startTime: Float,
        val description: String
)