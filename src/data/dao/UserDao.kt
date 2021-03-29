package data.dao

import com.google.gson.Gson
import data.dto.requests.UserRequest
import data.entities.UserEntity
import data.models.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun setNewUser(userRequest: UserRequest): User {
    val userId = UUID.randomUUID().toString()
    transaction {
        UserEntity.insert {
            it[id] = userId
            it[firstName] = userRequest.firstName.toString()
            it[middleName] = userRequest.middleName.toString()
            it[lastName] = userRequest.lastName.toString()
            it[phoneCountryPrefix] = userRequest.phoneCountryPrefix.toString()
            it[phoneNumber] = userRequest.phoneNumber.toString()
            it[email] = userRequest.email.toString()
            it[birthDate] = userRequest.birthDate.toString()
            it[sportRanks] = Gson().toJson(userRequest.sportRanks)
        }
    }
    return userRequest.run {
        User(
                id = userId,
                firstName, middleName, lastName, phoneCountryPrefix, phoneNumber, email
        )
    }
}