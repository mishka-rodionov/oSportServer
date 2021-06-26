package data.dto.requests

import com.google.gson.annotations.SerializedName
import data.dto.OrganizerDto
import domain.models.CompetitionState
import domain.models.Place

data class CompetitionRequest(
    @SerializedName("date")
    val date: String,
    @SerializedName("state")
    val state: CompetitionState? = null,
    @SerializedName("main_image")
    val mainImage: String? = null,
    @SerializedName("sport_type")
    val sportType: String,
    @SerializedName("place")
    val place: Place,
    @SerializedName("start_interval")
    val startInterval: Float,
    @SerializedName("organizers")
    val organizers: List<OrganizerDto>,
    @SerializedName("title")
    val title: String,
//    @SerializedName("participants_groups")
//    val participantGroups: List<String>,
    @SerializedName("description")
    val description: String? = null
)