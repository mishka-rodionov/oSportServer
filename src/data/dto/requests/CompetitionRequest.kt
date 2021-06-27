package data.dto.requests

import com.google.gson.annotations.SerializedName

data class CompetitionRequest(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("offset")
    val offset: Int = 0,
    @SerializedName("limit")
    val limit: Int = 10
)