package routes

import app.Settings.USER_REGISTER
import data.dao.setNewUser
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post

fun Routing.users() {
    post(USER_REGISTER) {
        val user = setNewUser(call.receive())
        call.respond(user)
    }
}