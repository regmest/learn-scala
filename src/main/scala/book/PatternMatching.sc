

// considers each case in order of declaration, once it matches it computates the corresponding expression
def dayOfWeek(n: Int): String = n match {
  case 1 => "Monday"
  case 2 => "Tuesday"
  case 3 => "Wednesday"
  case 4 => "Thursday"
  case 5 => "Friday"
  case 6 => "Saturday"
  case 7 => "Sunday"
  case _ => "Unknown" // default case
}
dayOfWeek(8)


// pattern matching supports types such as sealed traits, case classes)
def objInfo(param: Any): String = param match {
  case n: Int if n > 0 => s"$n is a positive Int"
  case d: Double => s"$d is a Double"
  case "ping" => "pong"
  case _: String => "you gave me a String"
  case obj =>s"Object: $obj"
}
objInfo(1)        // 1 is a positive Int
objInfo("ping")   // pong
objInfo(-1)       // Object: -1
objInfo(true)     // Object: true


sealed trait Currency
object USD extends Currency
object EUR extends Currency
object GBP extends Currency
object CAD extends Currency

def exchangeRateUSD(currency: Currency): Double = currency match {
  case USD => 1
  case GBP => 0.744
  case EUR => 0.848
}
val curr = EUR
exchangeRateUSD(curr)