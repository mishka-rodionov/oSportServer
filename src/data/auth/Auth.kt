package data.auth

import app.utils.datetimeToString
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.Claim
import com.auth0.jwt.interfaces.Payload
import io.ktor.application.ApplicationCall
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import java.lang.Exception
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val UTF8 = Charsets.UTF_8
val US_ASCII = Charsets.US_ASCII
val CLAIM_USER_ID = "userId"
val CLAIM_EXPIRE_TIMESTAMP = "expireTimestamp"
val CLAIM_TOKEN_VERSION = "version"
val CLAIM_TOKEN_VERSION_VALUE = 1

fun calcPasswordHash(password: String, salt: String, algorithm: String): String {
    // let's use a simpler approach for now
    if (algorithm == "SHA-512") {
        val md = MessageDigest.getInstance(algorithm)
        // md.reset()
        val text = salt + "$" + password
        return md.digest(text.toByteArray()).toString(US_ASCII)
    } else {
        throw NoSuchAlgorithmException("unknown hashing algorithm: $algorithm")
    }
}

fun hashAsHex(hash: String): String {
    val buffer = StringBuffer("0x")
    for (c in hash.toByteArray()) {
        buffer.append((c.toInt() + 128).toString(16).toUpperCase())
    }
    return buffer.toString()
}

fun hashFromHex(hex: String): String {
    if (!hex.startsWith("0x")) {
        throw Exception("hex string must start with 0x: $hex")
    }
    val buffer = mutableListOf<Byte>()
    var i = 2
    while (i < hex.length) {
        val next = i + 2
        var sub: String
        if (next < hex.length) {
            sub = hex.substring(i, next)
        } else {
            sub = hex.substring(i)
        }
        val byte = (sub.toInt(16) - 128).toByte()
        buffer.add(byte)
        i = next
    }
    return buffer.toByteArray().toString(UTF8)
}

fun calculateExpirationTimestamp(validityPeriod: Duration): String {
    val now = LocalDateTime.now()
    val expire = now.plus(validityPeriod)
    return datetimeToString(expire)
}

class SimpleJwtSingleton private constructor() {
    private object Holder { val INSTANCE = SimpleJwtSingleton() }
    companion object {
        val instance: SimpleJwtSingleton by lazy { Holder.INSTANCE }
    }

    private val algorithm = Algorithm.HMAC256(OSportConfig.instance.jwtSecret)
    val verifier = JWT
        .require(algorithm)
        .withAudience(OSportConfig.instance.jwtAudience)
        .withIssuer(OSportConfig.instance.jwtIssuer)
        .build()
    fun sign(userId: String): String = JWT.create()
        .withAudience(OSportConfig.instance.jwtAudience)
        .withIssuer(OSportConfig.instance.jwtIssuer)
        .withClaim(CLAIM_USER_ID, userId)
        .withClaim(CLAIM_EXPIRE_TIMESTAMP, calculateExpirationTimestamp(OSportConfig.instance.jwtTtl))
        .withClaim(CLAIM_TOKEN_VERSION, CLAIM_TOKEN_VERSION_VALUE)
        .sign(algorithm)

    fun decode(token: String): Map<String, String> {
        val decoded = JWT.decode(token)
        val claims = decoded.getClaims()
        return claims.map {
            it.key to it.value.asString()
        }.toMap()
    }

    fun tokenIsOk(token: String): Boolean {
        try {
            verifier.verify(token)
            return true
        } catch (e: JWTVerificationException) {
            return false
        }
    }

    fun checkAndDecode(token: String): Map<String, String> {
        if (!tokenIsOk(token)) {
            throw ApiException("failed to verify provided token")
        }
        return decode(token)
    }
}

fun checkExpiration(jwt: Payload): Boolean {
    val versionClaim: Claim = jwt.getClaim(CLAIM_TOKEN_VERSION)
    val version: Int? = versionClaim.asInt()
    if (version == null) {
        throw ApiException("failed to extract version from token")
    }
    if (version != CLAIM_TOKEN_VERSION_VALUE) {
        throw ApiException("token has unsupported version")
    }
    val expireClaim: Claim = jwt.getClaim(CLAIM_EXPIRE_TIMESTAMP)
    val expireString: String? = expireClaim.asString()
    if (expireString == null) {
        throw ApiException("failed to extract expiration timestamp from token")
    }
    val expireDateTime: LocalDateTime = LocalDateTime.parse(expireString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    val now: LocalDateTime = LocalDateTime.now()
    if (now.compareTo(expireDateTime) > 0) {
        return false
    }
    return true
}

fun getUserIdFromPrincipal(principal: JWTPrincipal): String {
    val jwt: Payload = principal.payload
    val userIdClaim: Claim = jwt.getClaim(CLAIM_USER_ID)
    val userId: String? = userIdClaim.asString()
    if (userId == null) {
        throw ApiException("failed to extract the claim $CLAIM_USER_ID")
    }
    if (!checkExpiration(jwt)) {
        throw ApiException("token has expired")
    }
    return userId
}

fun getUserIdFromApplicationCall(call: ApplicationCall): String {
    val principal: JWTPrincipal = call.authentication.principal<JWTPrincipal>()!!
    return getUserIdFromPrincipal(principal)
}
