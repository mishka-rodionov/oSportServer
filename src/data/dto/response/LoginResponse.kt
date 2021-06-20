package data.dto.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("phone_prefix")
    val phonePrefix: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("auth_token")
    val authToken: String
)