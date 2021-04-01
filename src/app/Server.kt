package app

import io.ktor.server.engine.EngineAPI

@EngineAPI
fun main() {
    setup().start(wait = true)
}
