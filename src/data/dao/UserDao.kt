package data.dao

import data.models.User

interface UserDao {
    fun setUser(userEntity: User)
}