package app

import app.Settings.SERVER_PORT
import app.di.daoModule
import app.di.repositoryModule
import data.auth.OSportConfig
import data.auth.SimpleJwtSingleton
import data.auth.checkExpiration
import domain.CompetitionRepository
import domain.UserRepository
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing
import io.ktor.server.engine.*
import io.ktor.server.netty.Netty
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import routes.competitions
import routes.users

@EngineAPI
fun setup(): BaseApplicationEngine {
    setDatabaseConnection()
    return server(Netty)
}

@EngineAPI
fun server(engine: ApplicationEngineFactory<BaseApplicationEngine,
        out ApplicationEngine.Configuration>): BaseApplicationEngine {
    return embeddedServer(
            engine,
            port = SERVER_PORT,
            watchPaths = listOf("mainModule"),
            module = Application::mainModule
    )
}

fun Application.mainModule() {
    val kodein = Kodein{
        import(repositoryModule)
        import(daoModule)
    }

    val userRepository by kodein.instance<UserRepository>()
    val competitionRepository by kodein.instance<CompetitionRepository>()

    install(Authentication) {
        jwt {
            verifier(SimpleJwtSingleton.instance.verifier)
            validate { credential ->
                val ok: Boolean = (
                        credential.payload.audience.contains(OSportConfig.instance.jwtAudience) &&
                                checkExpiration(credential.payload)
                        )
                if (ok) JWTPrincipal(credential.payload) else null
            }
        }
    }

    install(ContentNegotiation) {
        gson()
    }
    routing {
        authenticate {
            this@routing.competitions(competitionRepository)
        }
        users(userRepository)
//        competitions(competitionRepository)
    }
}