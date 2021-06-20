package data.repository

import data.auth.ApiException
import data.auth.SimpleJwtSingleton
import data.auth.calcPasswordHash
import data.dao.UserDao
import data.dto.requests.LoginRequest
import data.dto.response.LoginResponse
import domain.UserRepository
import domain.models.User

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override fun setNewUser(newUser: User) {

        userDao.setUser(userEntity = newUser)
    }

    override fun userLogin(loginParams: LoginRequest): LoginResponse {
        val user = userDao.getUser(loginParams.phone) ?: throw ApiException("user ${loginParams.phone} is not found")

        val hash = calcPasswordHash(loginParams.password, user.salt, "SHA-512")
        println("hash = $hash")
        println("user.passwordHash = ${user.passwordHash}")
        if (hash != user.passwordHash) {
            println("incorrect password")
            throw ApiException("incorrect password")
        }

        /* this token only identifies a user, but knows nothing about the user's rights */

        val idToken = SimpleJwtSingleton.instance.sign(user.id)
        return LoginResponse(userId = user.id, phonePrefix = user.phoneCountryPrefix, phone = user.phoneNumber, authToken = idToken)
    }
}