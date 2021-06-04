package app.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun datestampToLocalDate(datestamp: Int): LocalDate {
    val year = datestamp / 10000
    assert(2000 <= year && year < 2100)
    val r1 = datestamp - year * 10000
    val month = r1 / 100
    assert(1 <= month && month <= 12)
    val r2 = r1 - month * 100
    val dayOfMonth = r2
    assert(1 <= dayOfMonth && dayOfMonth <= 31)
    return LocalDate.of(year, month, dayOfMonth)
}

fun datetimeToString(datetime: LocalDateTime): String {
    return datetime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}

fun datetimeFromString(datetime: String): LocalDateTime {
    return LocalDateTime.parse(datetime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}
