package domain.models

import com.google.gson.annotations.SerializedName

data class Place(
        @SerializedName("name")
        val name: String,
        @SerializedName("coordinates")
        val coordinates: Coordinate
)