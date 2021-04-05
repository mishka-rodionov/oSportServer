package app

object Settings {
    /** Database postgres */
    const val DB_NAME = "osport"
    const val DB_USERNAME = "postgres"
    const val DB_PASSWORD = "rm1989anm"

    /** Server settings*/
    const val SERVER_PORT = 8080

    /** User Routes */
    const val USER_REGISTER = "/user/new"

    /** Competition Routes*/
    const val COMPETITION_NEW = "/competition/new"
    const val PARTICIPANT_NEW = "competition/participant/new"

}