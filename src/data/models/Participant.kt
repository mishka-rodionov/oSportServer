package data.models

import java.util.*

data class Participant(
        val id: String,
        val registrationDate: Date? = null,
        val isPayed: Boolean? = null
)