package com.rodionov.data.models

import java.util.*
import kotlin.collections.HashMap

data class User(
        val userId: String,
        val firstName: String? = null,
        val middleName: String? = null,
        val lastName: String? = null,
        val phoneCountryPrefix: String? = null,
        val phoneNumber: String? = null,
        val email: String? = null,
        val birthDate: Date? = null,
        val sportRanks: HashMap<Sport, SportRank>? = null
)