package data.dao

import com.google.gson.Gson
import data.entities.UserEntity
import data.models.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserDaoImpl: UserDao {
    override fun setUser(user: User) {
        val userId = UUID.randomUUID().toString()
        transaction {
            UserEntity.insert {
                it[id] = userId
                it[firstName] = user.firstName.toString()
                it[middleName] = user.middleName.toString()
                it[lastName] = user.lastName.toString()
                it[phoneCountryPrefix] = user.phoneCountryPrefix.toString()
                it[phoneNumber] = user.phoneNumber.toString()
                it[email] = user.email.toString()
                it[birthDate] = user.birthDate.toString()
                it[sportRanks] = Gson().toJson(user.sportRanks)
            }
        }
//        return userRequest.run {
//            User(
//                id = userId,
//                firstName, middleName, lastName, phoneCountryPrefix, phoneNumber, email
//            )
//        }
    }
}

