package data.mappers

import app.utils.DateTimeFormatter
import data.dto.requests.UserRequest
import data.entities.UserEntity
import domain.models.User
import org.jetbrains.exposed.sql.ResultRow

object UserMapper {

    fun toModel(userRequest: UserRequest, userId: String) = userRequest.run{
        User(
            id = userId,
            firstName, middleName, lastName, phoneCountryPrefix, phoneNumber, email
        )
    }

    fun toModel(row: ResultRow) = row.run {
        User(
            id = row[UserEntity.id],
            firstName = row[UserEntity.firstName],
            middleName = row[UserEntity.middleName],
            lastName = row[UserEntity.lastName],
            phoneCountryPrefix = row[UserEntity.phoneCountryPrefix],
            phoneNumber = row[UserEntity.phoneNumber],
            email = row[UserEntity.email],
            birthDate = DateTimeFormatter.parse(row[UserEntity.birthDate]),
            sportRanks = null
        )
    }

}