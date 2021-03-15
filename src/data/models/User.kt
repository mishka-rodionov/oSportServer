package data.models

import com.google.gson.annotations.SerializedName
import data.models.Sport
import data.models.SportRank
import java.util.*
import kotlin.collections.HashMap

data class User(
        @SerializedName("user_id")
        val userId: String,
        @SerializedName("first_name")
        val firstName: String? = null,
        @SerializedName("middle_name")
        val middleName: String? = null,
        @SerializedName("last_name")
        val lastName: String? = null,
        @SerializedName("phone_country_prefix")
        val phoneCountryPrefix: String? = null,
        @SerializedName("phone_number")
        val phoneNumber: String? = null,
        @SerializedName("email")
        val email: String? = null,
        @SerializedName("birth_date")
        val birthDate: Date? = null,
        @SerializedName("sport_ranks")
        val sportRanks: HashMap<Sport, String>? = null
)