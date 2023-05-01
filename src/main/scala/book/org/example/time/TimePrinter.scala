package book.org.example.time

import java.time.format.DateTimeFormatter
import java.time.{ZoneId, ZonedDateTime}

class TimePrinter(formatter: DateTimeFormatter) {  // implementation works with any formatter

  def now(timezone: String):String = {
    val dateTime = currentDateTime(timezone)
    dateTimeToString(dateTime)
  }

  private def currentDateTime(timezone: String): ZonedDateTime = { // gets now time for given timezone
    val zoneId = ZoneId.of(timezone)
    ZonedDateTime.now(zoneId)
  }

  private def dateTimeToString(dateTime: ZonedDateTime): String = formatter.format(dateTime)

}
