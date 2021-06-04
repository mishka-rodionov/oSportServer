package routes

import app.Settings.USER_LOGIN
import app.Settings.USER_REGISTER
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

    }

}