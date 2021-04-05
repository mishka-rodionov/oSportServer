package data.models

import java.util.*

data class Participant(
        val userId: String,
        val competitionId: String,
        val registrationDate: Date,
        val isPaid: Boolean? = null
)