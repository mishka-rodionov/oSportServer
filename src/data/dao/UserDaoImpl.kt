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
                it[passwordHash] = userEntity.passwordHash
                it[salt] = userEntity.salt
            }
        }
    }

    override fun getUser(phone: String): User? {
        println("getUser: phone = $phone")
        val phoneNumber = phone.substring(2, phone.length)
        println("getUser: phoneNumber = $phoneNumber")
        return transaction {
            UserMapper.toModel(UserEntity.select { UserEntity.phoneNumber eq phone }.first())
        }
    }

    override fun getUserById(id: String) = transaction {
        println("user id = $id")
            UserMapper.toModel(UserEntity.select { UserEntity.id eq id }.first())
        }
}

