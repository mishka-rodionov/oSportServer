package domain.models

data class Login(
    val phoneCountryPrefix: String,
    val phone: String,
    val password: String
)