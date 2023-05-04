import scala.annotation.tailrec

val a = "hello"
a match {
  case "bye" => "see you!"
  case "hello" => "hi!"
}

// considers each case in order of declaration, once it matches it computates the corresponding expression
def dayOfWeek(n: Int): String = n match {
  case 1 => "Monday"
  case 2 => "Tuesday"
  case 3 => "Wednesday"
  case 4 => "Thursday"
  case 5 => "Friday"
  case 6 => "Saturday"
  case 7 => "Sunday"
  case _ => "Unknown" // default case, or there'll be MatchError
}
dayOfWeek(8)


// pattern matching supports types such as sealed traits, case classes)
def objInfo(param: Any): String = param match {
  case n: Int if n > 0 => s"$n is a positive Int"
  case -1 | -2 => s"-1 or -2"
  case d: Double => s"$d is a Double"
  case "ping" => "pong"
  case _: String => "you gave me a String"
  case obj => s"Object: $obj"
}
objInfo(1)        // 1 is a positive Int
objInfo("ping")   // pong
objInfo(-1)       // -1 or -2
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


// pattern matching с case class
// используется не только для того, чтобы найти совпадения, но и для того,
// чтобы распаковать какие-то структуры и сразу их использовать
case class Address(country: String, city: String)
def addressInfo(address: Address): String = address match {
  case Address("Russia", "Moscow") => "russian capital"
  case Address("Russia", _) => "russian"
  case Address("Japan", "Tokyo") => "japanese capital"
  case Address("Japan", city) => s"japanese $city"
  case _ => "no info"
}


// посчитаем сумму элеменртов в списке с помощью сопоставления с образцом
// но этот способ будет работаь только для связного списка, а не для любых коллекций
@tailrec
def sum(xs: List[Int], start: Int = 0): Int = xs match {
  // сопоставляем наш список с непустым списком, отделяя голову и хвост
  case x :: rest => sum(rest, start + x)
  // сопоставляем список с пустым списком
  case Nil => start
}

// до 3х элементов
def sum(xs: List[Int], start: Int = 0): Int = xs match {
  case List() => start                  // все пустые списки
  case List(x) => start + x             // все списки из 1го элемента
  case List(x, y) => start + x + y      // все списки из 2х элементов
  case List(x, y, z) => start + x + y + z
  case _ => throw new Exception("too many elements!")
}

// неограниченное число элементов
def sum(xs: List[Int], start: Int = 0): Int = xs match {
  case List() => start
  case List(x, rest@_*) => sum(rest.toList, start + x)
}

sum(List(1,2,3,4))
// можно использовать не только для списка
def sum(xs: Seq[Int], start: Int = 0): Int = xs match {
  case Seq() => start
  case Seq(x, rest@_*) => sum(rest, start + x)
}
sum(Seq(1,2,3,4))


// использование регулярных выражений
// используем для матчинга
val address = "\\w+, \\w+".r  // 2 слова через запятую
def isAddress(string: String): Boolean = string match {
  case address() => true   // используем регулярное выражыние
  case _ => false
}
isAddress("Russia, Moscow") // true
isAddress("Soviet Union")   // false
// используем для "распаковки"
val address1 = "(\\w+), (\\w+)".r // с помощью () объявляем группы
case class Address(country: String, city: String)
def readAddress(string: String): Option[Address] = string match {
  // заматченные группы передаются в кейс-класс
  case address1(country, city) => Some(Address(country, city))
  case _ => None
}
readAddress("japan, tokyo")
// разложить строку на элементы по регекспу
val address2 = "(\\w+), (\\w+)".r
def readAddress(string: String): List[String] = string match {
  // чтобы не перечислять все группы с помощью переменных, можно заматчить элементы с помощью @_*
  case address2(parts@_*) =>parts.toList
  case _ => List()
}
readAddress("japan, tokyo")
readAddress("japan, tokyo, street")

// комбинация
val regex = "(Russia|Japan)".r
case class Address(country: String, city: String)
def getInfo(address: Address): String = address match {
//  case Address(regex(country), city) => s"$city, $country"
  case Address(country@regex(_), city) => s"$city, $country" // немного другая запись
  case Address(_, _) => "unknown country"
}
getInfo(Address("Japan", "Tokyo"))   // Tokyo, Japan
getInfo(Address("China", "Beijing")) // unknown country