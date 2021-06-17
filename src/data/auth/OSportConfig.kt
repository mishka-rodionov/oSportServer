package data.auth

import java.time.DayOfWeek
import java.time.Duration
import java.time.Period

/**
 * This class serves as a single place to define application-wide details
 */
class OSportConfig private constructor() {
    private object Holder { val INSTANCE = OSportConfig() }
    companion object {
        val instance: OSportConfig by lazy { Holder.INSTANCE }
    }

    val apiPort: Int = (System.getenv("API_PORT") ?: "3999").toInt()

    val mainDomain: String = "osport.com"

    val emailDomain: String = ""
    val mailgunApiKey: String = System.getenv("MAILGUN_API_KEY") ?: ""
    val mailgunEndpoint: String = "api.eu.mailgun.net"

    val smscLogin: String = System.getenv("SMSC_LOGIN") ?: ""
    val smscPsw: String = System.getenv("SMSC_PSW") ?: ""

    val jwtSecret: String = System.getenv("JWT_SECRET") ?: "my-super-secret-for-jwt-071"
    val jwtIssuer: String = System.getenv("JWT_ISSUER") ?: "https://$mainDomain/"
    val jwtAudience: String = "hive-jwt-audience"
    val jwtRealm: String = "hive api realm"
    val jwtTtl: Duration = Duration.ofDays(30)

    val allowedShipmentWeekdays: List<DayOfWeek> = listOf(DayOfWeek.WEDNESDAY, DayOfWeek.SATURDAY)
    val preorderMaxPeriod: Period = Period.ofDays(30)
}
