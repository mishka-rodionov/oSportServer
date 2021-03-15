package data.models

import com.google.gson.annotations.SerializedName

data class Sport (
        @SerializedName("sport_id")
        val sportId: String,
        @SerializedName("name")
        val name: String? = null
)