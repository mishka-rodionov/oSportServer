package domain

import data.dto.requests.LoginRequest
import data.dto.response.LoginResponse
import domain.models.User

interface UserRepository {
    fun setNewUser(newUser: User)
    fun userLogin(loginParams: LoginRequest): LoginResponse
}