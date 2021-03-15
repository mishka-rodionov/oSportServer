package com.rodionov

import io.ktor.application.*
import io.ktor.auth.Authentication
import io.ktor.client.HttpClient
import io.ktor.client.engine.jetty.Jetty
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>): Unit {
    embeddedServer(Netty, 8080) {
        println("database = ")
//        setDatabaseConnection()

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

