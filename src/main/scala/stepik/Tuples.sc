
// Тип кортежа определяется содержимым
val tuple = (1,2,3)             // (Int, Int, Int)
val tuple = (1,"2",true,false)  // (Int, String, Boolean, Boolean)


// Кортежи используются, чтобы например возвращать из функции несколько значений сразу.
def divMod(x: Int, y: Int): (Int, Int) =
  (x / y, x % y)
divMod(5, 2)

def firstLastAndCount(line: String): (Char, Char, Int) =
  (line.head, line.last, line.length)
firstLastAndCount("hello")


// Распаковка кортежа
val (div, mod) = divMod(5, 2)
div // 2
mod // 1
val (first, last, count) = firstLastAndCount("hello")

def showDiv(x: Int, y: Int) =
  divMod(x, y) match {
    case (d, r) => s"$x = $d * $y + $r"
  }
showDiv(5, 2) // 5 = 2 * 2 + 1


// Обращение к полям кортежа
val tuple = (1,"scala",false)
tuple._1
tuple._2


// Пара - кортеж из двух элементов. Имеет свой синтаксис.
val NameAndPopulation = "Moscow" -> 12e6
val pairs = List(1 -> "one", 2 -> "two", 3 -> "three")
// поменять элементы местами
val pair = 1 -> "one" // (Int, String) = (1,one)
pair.swap             // (String, Int) = (one,1)


// Алгоритм Евклида: a*x + b*y = d, где d - наибольший общий делитель
// Напишем функцию, которая находит x и y
def euclid(a: Int, b: Int): (Int, Int) = {
  if(b > a) euclid(b, a).swap // хотим, чтобы всегда a было больше b
  else if (b == 0) (1, 0)
  else {
    val d = a / b // частное
    val r = a % b // остаток от деления
    val (x, y) = euclid(b, r)
    (y, x - d * y)
  }
}
euclid(7, 4)


def swap3(tuple: (Int, Int, Int)): (Int, Int, Int) = (tuple._3, tuple._2, tuple._1)
//def swap3(tuple: (Int, Int, Int)): (Int, Int, Int) = { case (a, b, c) => (c, b, a) }
swap3((1, 2, 3)) == (3, 2, 1)