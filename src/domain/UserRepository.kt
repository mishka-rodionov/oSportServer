package domain

import data.models.User

interface UserRepository {
    fun setNewUser(newUser: User)
    fun userLogin(loginParams: LoginParams)
}