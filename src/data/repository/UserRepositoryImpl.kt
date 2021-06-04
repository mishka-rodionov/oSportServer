package data.repository

import data.auth.ApiException
import data.auth.SimpleJwtSingleton
import data.auth.calcPasswordHash
import data.dao.UserDao
import domain.UserRepository
import domain.models.Login
import domain.models.User

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override fun setNewUser(newUser: User) {
        userDao.setUser(newUser)
    }

    override fun userLogin(loginParams: Login) {
        val user = userDao.getUser(loginParams.phone)

        if (user == null) {
            throw ApiException("user ${loginParams.phone} is not found")
        }

        val hash = calcPasswordHash(loginParams.password, "", "SHA-512")
        if (hash != user.passwordHash) {
            throw ApiException("incorrect password")
        }

        /* this token only identifies a user, but knows nothing about the user's rights */
        val idToken = SimpleJwtSingleton.instance.sign(user.id)
    }
}