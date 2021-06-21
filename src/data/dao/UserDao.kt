package data.dao

import domain.models.User

interface UserDao {
    fun setUser(userEntity: User)
    fun getUser(phone: String): User?
    fun getUserById(id: String): User?
}