package data.dao

import com.google.gson.Gson
import data.entities.UserEntity
import data.mappers.UserMapper
import domain.models.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserDaoImpl : UserDao {
    override fun setUser(userEntity: User) {
        transaction {
            UserEntity.insert {
                it[id] = userEntity.id
                it[firstName] = userEntity.firstName.toString()
                it[middleName] = userEntity.middleName.toString()
                it[lastName] = userEntity.lastName.toString()
                it[phoneCountryPrefix] = userEntity.phoneCountryPrefix.toString()
                it[phoneNumber] = userEntity.phoneNumber.toString()
                it[email] = userEntity.email.toString()
                it[birthDate] = userEntity.birthDate.toString()
                it[sportRanks] = Gson().toJson(userEntity.sportRanks)
            }
        }
    }

    override fun getUser(phone: String): User {
        return transaction {
            UserMapper.toModel(UserEntity.select { UserEntity.phoneNumber eq phone }.first())
        }
    }
}

