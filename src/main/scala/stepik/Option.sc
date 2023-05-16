import scala.annotation.tailrec

def divide(x: Int, y: Int): Option[Int] =
  if(y == 0) None else Some(x / y)

val divResult: Option[Int] = divide(10,2) // val res0: Option[Int] = Some(5)


// распаковываем получившееся значение в новую переменную, чтобы работать с ней дальше
def ShowDivide(x: Int, y: Int): String =
  divide(x, y) match {
    case Some(d) => s"$x / $y = $d"
    case None => "zero division"
  }
ShowDivide(10,0) // "zero division"

// переписали с помощью методов, описанный ниже
def ShowDivide(x: Int, y: Int): String =
  divide(x, y)
    .map(d => s"$x / $y = $d")
    .getOrElse("zero division")
ShowDivide(10,0) // "zero division"


// замена пустого значения
// getOrElse вычисляется только в случае None
divide(7, 0).getOrElse(1) // res1: Int = 1
divide(7, 1).getOrElse(1) // res2: Int = 7
// orElse
divide(7, 0).orElse(divide(7, 2)) // res3: Option[Int] = Some(3)


// преобразования без распаковки
// map - если есть значение, то преобразует, если нет, то оставит пустым
divide(7, 0).map(x => x / 2)              // res4: Option[Int] = None
divide(7, 1).map(x => x / 2)              // res5: Option[Int] = Some(3)

// flatMap - если есть значение, то применит optional-функицю
divide(7, 0).flatMap(x => divide(x, 2))   // res5: Option[Int] = None
divide(7, 1).flatMap(x => divide(x, 2))   // res7: Option[Int] = Some(3)

// filter - если значение есть, то выполнит проверку соответствия условию
divide(7, 0).filter(x => x > 2)           // res8: Option[Int] = None
divide(7, 1).filter(x => x > 2)           // res9: Option[Int] = Some(7)

// collect - комбинация filter + map
divide(7, 0).collect{
  case x if x > 4 => x / 2                // res10: Option[Int] = None
}



// практика
// у нас есть строчка, из которой мы хотим вырезать часть в скобках
val string = "[stepik]"
// можем найти индекс скобки
string.indexOf("[")           // val res12: Int = 0
// но если попробуем этим методом найти что-то, чего в строке нет, вернет -1
string.indexOf("0")           // val res13: Int = -1
// хотим, чтобы возвращался не -1, а None, что менее странно
// напишем функцию
def indexOf(s: String, pattern: String): Option[Int] =
  Option(s.indexOf(pattern)).filter(_ >= 0)

indexOf(string, "0")   // val res14: Option[Int] = None

// теперь хотим функцию, возвращающую пару индексов - индекс "[" и "]"
def indexOfBrackets(s: String): Option[(Int, Int)] =
  indexOf(s, "[")
    .flatMap { // если нашли одну скобку, ищем вторую
      iFirstBracket =>
        indexOf(s, "]")
          .filter(_ > iFirstBracket) // хотим, чтобы в получившейся паре первый индекс был меньше второго
          .map(iSecondBracket => (iFirstBracket, iSecondBracket))
    }
indexOfBrackets("[stepik]") // res15: Option[(Int, Int)] = Some((0,7))
indexOfBrackets("[stepik")  // res16: Option[(Int, Int)] = None
indexOfBrackets("stepik")   // res17: Option[(Int, Int)] = None
indexOfBrackets("]stepik[") // res18: Option[(Int, Int)] = None

// итоговая функция, вырезающая текст в скобках
def cutBrackets(s: String): Option[String] =
  indexOfBrackets(s).map{
    // распаковываем и вырезаем подстрочку
    case (opening, closing) => s.substring(opening + 1, closing)
  }

cutBrackets("[stepik]")     // res19: Option[String] = Some(stepik)
indexOfBrackets("]stepik[") // res20: Option[(Int, Int)] = None




List(1,2,3,4,5,6).find(x => x % 3 == 0).getOrElse(0) * 2
