package book.org.example.time

import java.time.format.DateTimeFormatter
import scala.io.StdIn

object TimeApp extends App {

  val timezone = StdIn.readLine("Give me a timezone: ") // ex.g. Europe/Paris

  val timePrinter = new TimePrinter(DateTimeFormatter.RFC_1123_DATE_TIME) // Mon, 1 May 2023 15:23:31 +0200

  println(timePrinter.now(timezone))

}
