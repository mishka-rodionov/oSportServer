package data.dto.requests

import com.google.gson.annotations.SerializedName

data class ParticipantRequest(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("competition_id")
    val competitionId: String,
    @SerializedName("registration_date")
    val registrationDate: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("group")
    val group: String,
    @SerializedName("is_paid")
    val isPaid: Boolean? = null
)