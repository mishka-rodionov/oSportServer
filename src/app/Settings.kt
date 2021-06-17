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
    const val USER_LOGIN = "/user/login"

    /** Competition Routes*/
    const val COMPETITION_NEW = "/competition/new"
    const val PARTICIPANT_NEW = "competition/participant/new"
    const val GENERATE_START_LISTS = "competition/generate/start_list"
    const val GET_PARTICIPANTS = "competition/participants"
    const val GET_START_LIST = "competition/start_list"

}