package app.extensions

import data.dto.response.UserResponse
import data.entities.UserEntity
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toUserResponse(): UserResponse = this.run {
    UserResponse(
        id = this[UserEntity.id],
        firstName = this[UserEntity.firstName],
        middleName = this[UserEntity.middleName],
        lastName = this[UserEntity.lastName],
        phoneCountryPrefix = this[UserEntity.phoneCountryPrefix],
        phoneNumber = this[UserEntity.phoneNumber],
        email = this[UserEntity.email],
        birthDate = null,
        sportRanks = null
    )
}