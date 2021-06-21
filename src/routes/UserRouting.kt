package routes

import app.Settings.USER_GET_BY_ID
import app.Settings.USER_LOGIN
import app.Settings.USER_REGISTER
import data.auth.ApiException
import data.dto.requests.UserRequest
import data.mappers.CommonMapper
import data.mappers.UserMapper
import domain.UserRepository
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import java.util.*

fun Routing.users(userRepository: UserRepository) {
    post(USER_REGISTER) {
        val userId = UUID.randomUUID().toString()
        userRepository.setNewUser(UserMapper.toModel(call.receive(), userId))
        call.respond(CommonMapper.toIdDto(userId))
    }

    post(USER_LOGIN) {
        try {
            call.respond(userRepository.userLogin(call.receive()))
        } catch (e: ApiException) {
            println("api exception")
        }

    }

    post(USER_GET_BY_ID) {
        try {
            call.respond(
                UserMapper.toResponse(
                    userRepository.getUserById(
                        CommonMapper.toId(call.receive())
                    ) ?: throw ApiException("user not found")
                )
            )
        } catch (e: ApiException) {
            println("api exception: message = ${e.apiMessage}")
        }
    }

}