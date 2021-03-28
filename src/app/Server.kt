package app

import com.google.gson.Gson
import data.dto.requests.UserRequest
import data.models.Sport
import data.models.SportRank
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.client.HttpClient
import io.ktor.client.engine.jetty.Jetty
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

fun main(args: Array<String>): Unit {
//    val json = Gson().toJson(UserRequest(sportRanks = hashMapOf(
//            Sport(sportId = "123", name = "Orienteering").toString() to SportRank.MASTER_OF_SPORT.name,
//            Sport(sportId = "123", name = "Cross Country").toString() to SportRank.HONORED_MASTER_OF_SPORT.name
//    )))
//    println("json = $json")
//    val user = Gson().fromJson<UserRequest>(json)
//    println("user = $user")


    embeddedServer(Netty, 8080) {
        println("database = ")

        setDatabaseConnection()

        install(Authentication) {
        }

        install(ContentNegotiation) {
            gson()
        }

        val client = HttpClient(Jetty) {
        }

        routing {

        }

    }.start(wait = true)
}
