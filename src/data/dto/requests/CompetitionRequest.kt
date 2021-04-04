package data.dto.requests

import com.google.gson.annotations.SerializedName
import data.dto.OrganizerDto
import data.models.CompetitionState
import data.models.Place
import data.models.Sport

data class CompetitionRequest(
    @SerializedName("date")
    val date: String,
    @SerializedName("state")
    val state: CompetitionState? = null,
    @SerializedName("main_request")
    val mainImage: String? = null,
    @SerializedName("sport_type")
    val sportType: Sport,
    @SerializedName("place")
    val place: Place,
    @SerializedName("organizers")
    val organizers: List<OrganizerDto>,
    @SerializedName("description")
    val description: String? = null
)