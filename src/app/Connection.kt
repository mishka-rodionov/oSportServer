package app

import app.Settings.DB_NAME
import app.Settings.DB_PASSWORD
import app.Settings.DB_USERNAME
import data.entities.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun setDatabaseConnection() {
    val temp = Database.connect(
        "jdbc:postgresql://localhost:5432/$DB_NAME", driver = "org.postgresql.Driver",
        user = DB_USERNAME, password = DB_PASSWORD
    )

    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(UserEntity, CompetitionEntity, OrganizerEntity, StartListEntity, FinishListEntity)
    }

    println("database = ${temp.url}")
}