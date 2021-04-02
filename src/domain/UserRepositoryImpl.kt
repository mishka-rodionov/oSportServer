package domain

import data.dao.UserDao
import data.models.User

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override fun setNewUser(newUser: User) {
        userDao.setUser(newUser)
    }
}