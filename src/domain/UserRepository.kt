package domain

import domain.models.Login
import domain.models.User

interface UserRepository {
    fun setNewUser(newUser: User)
    fun userLogin(loginParams: Login)
}