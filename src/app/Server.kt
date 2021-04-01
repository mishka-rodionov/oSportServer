package app

import data.dao.setNewUser
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.client.HttpClient
import io.ktor.client.engine.jetty.Jetty
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>): Unit {

    embeddedServer(Netty, 8080) {
        println("database = ")

        setDatabaseConnection()

        install(Authentication) {
        }

        install(ContentNegotiation) {
            gson()
        }

        routing {
            post("/user/new") {
                val user = setNewUser(call.receive())
                call.respond(user)
            }
            post("/competition/new"){

            }
        }

    }.start(wait = true)
}
