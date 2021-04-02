package app

import io.ktor.server.engine.EngineAPI

@EngineAPI
fun main(args: Array<String>): Unit {
    setup().start(wait = true)
}
