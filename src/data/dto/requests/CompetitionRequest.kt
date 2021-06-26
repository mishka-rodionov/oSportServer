package data.dto.requests

import com.google.gson.annotations.SerializedName

data class CompetitionRequest(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("skip")
    val skip: Int = 0,
    @SerializedName("limit")
    val limit: Int = 10
)