package domain

import data.models.User

interface UserRepository {
    fun setNewUser(newUser: User)
}