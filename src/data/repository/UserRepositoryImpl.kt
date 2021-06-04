package data.repository

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
    }
}