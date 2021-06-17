package data.mappers

import app.utils.DateTimeFormatter
import data.auth.calcPasswordHash
import data.dto.requests.UserRequest
import data.entities.UserEntity
import domain.models.User
import org.jetbrains.exposed.sql.ResultRow

object UserMapper {

    fun toModel(userRequest: UserRequest, userId: String) = userRequest.run{
        val salt = java.util.UUID.randomUUID().toString()
        val passwordHash = calcPasswordHash(password, salt, "SHA-512")
        User(
            id = userId,
            firstName, middleName, lastName, phoneCountryPrefix, phoneNumber, email, null, null, passwordHash, salt
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
//            birthDate = DateTimeFormatter.parse(row[UserEntity.birthDate]),
            birthDate = null,
            sportRanks = null,
            passwordHash = row[UserEntity.passwordHash],
            salt = row[UserEntity.salt]
        )
    }

}