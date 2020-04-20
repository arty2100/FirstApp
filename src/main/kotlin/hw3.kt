import java.time.Year
import java.time.YearMonth
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.abs

enum class TYPE {
    YEAR, MONTH, DAY, HOUR, MINUTE
}

fun main() {
    val publishedAgo: Long = 120

    println("Заходил последний раз ${lastSeen(publishedAgo)}")
}

fun lastSeen(seconds: Long): String {

    val instance = Calendar.getInstance()
    val currentDate = Date(instance.timeInMillis).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    val lastSeenDate =
        Date(instance.timeInMillis - (seconds * 1000)).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

    val yearsBetween = ChronoUnit.YEARS.between(Year.from(lastSeenDate), Year.from(currentDate))
    val monthsBetween = ChronoUnit.MONTHS.between(YearMonth.from(lastSeenDate), YearMonth.from(currentDate))
    val daysBetween = ChronoUnit.DAYS.between(lastSeenDate, currentDate)
    val hoursBetween = ChronoUnit.HOURS.between(lastSeenDate, currentDate)
    val minutesBetween = ChronoUnit.MINUTES.between(lastSeenDate, currentDate)

    when {
        yearsBetween > 0 -> {

            return getResponse(yearsBetween, TYPE.YEAR)
        }
        monthsBetween > 0 -> {
            return getResponse(monthsBetween, TYPE.MONTH)
        }
        daysBetween > 0 -> {
            return getResponse(daysBetween, TYPE.DAY)
        }
        hoursBetween > 0 -> {
            return getResponse(hoursBetween, TYPE.HOUR)
        }
        minutesBetween > 0 -> {
            return getResponse(minutesBetween, TYPE.MINUTE)
        }
        else -> {
            return "меньше минуты назад"
        }
    }
}

fun getResponse(value: Long, type: TYPE): String {

    var returnString = ""

    val lastDigit = value % 10
    val secondToLastDigit = abs((value%100)/10)

    when {
        lastDigit == 1L && secondToLastDigit != 1L -> {
            when {
                TYPE.YEAR == type -> {
                    returnString = "год назад"
                }
                TYPE.MONTH == type -> {
                    returnString = "мecяц назад"
                }
                TYPE.DAY == type -> {
                    returnString = "день назад"
                }
                TYPE.HOUR == type -> {
                    returnString = "час назад"
                }
                TYPE.MINUTE == type -> {
                    returnString = "минуту назад"
                }
            }
        }
        (lastDigit == 2L || lastDigit == 3L || lastDigit == 4L) && (secondToLastDigit != 1L) -> {
            when {
                TYPE.YEAR == type -> {
                    returnString = "года назад"
                }
                TYPE.MONTH == type -> {
                    returnString = "мecяца назад"
                }
                TYPE.DAY == type -> {
                    returnString = "дня назад"
                }
                TYPE.HOUR == type -> {
                    returnString = "часа назад"
                }
                TYPE.MINUTE == type -> {
                    returnString = "минуты назад"
                }
            }
        }
        else -> {
            when {
                TYPE.YEAR == type -> {
                    returnString = "лет назад"
                }
                TYPE.MONTH == type -> {
                    returnString = "мecяцев назад"
                }
                TYPE.DAY == type -> {
                    returnString = "дней назад"
                }
                TYPE.HOUR == type -> {
                    returnString = "часов назад"
                }
                TYPE.MINUTE == type -> {
                    returnString = "минут назад"
                }
            }
        }
    }
    return if (lastDigit == 1L && secondToLastDigit != 1L && value<=1) {
        returnString
    } else "$value $returnString"
}
