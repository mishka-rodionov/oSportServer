package routes

import app.Settings.COMPETITION_NEW
import io.ktor.routing.Routing
import io.ktor.routing.post

fun Routing.competitions() {
    post(COMPETITION_NEW){

    }
}