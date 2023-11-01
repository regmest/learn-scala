
// suppose we have two functions that are partly similar
def sqrtOrZero(n: Int): Double = n match {
  case x if x >= 0 => Math.sqrt(x)
  case _ => 0
}
def sqrtOrValue(n: Int): Double = n match {
  case x if x >= 0 => Math.sqrt(x)
  case x => x
}
// we can avoid code duplication using partial function
val sqrt:  PartialFunction[Int, Double] = { case x if x >= 0 => Math.sqrt(x) }
val zero:  PartialFunction[Int, Double] = { case _ => 0 }
val value: PartialFunction[Int, Double] = { case x => x }
// пересобираем функции с помощью partial functions
def sqrtOrZeroNew(n: Int): Double = sqrt.orElse(zero)(n)
def sqrtOrValueNew(n: Int): Double = sqrt.orElse(value)(n)

sqrtOrZero(-5)
sqrtOrZeroNew(-5)
sqrtOrValue(-5)
sqrtOrValueNew(-5)

// исключения тоже относятся к partial functions
class MyException(msg: String) extends Exception(msg)
//throw new MyException("BOOM!")

def n(): Int = {
  try {
    println("THROW EXCEPTION")
    throw new Exception("BOOM!")
    42
  }
  catch { // w/o catch all code just fails; w catch code doesn't fail, just prints "Ignoring ..."
    case ex: Exception =>
      println(s"Ignoring exception $ex. Returning zero instead.")
      0
  }
  finally {
    println("FINALLY")
  }
}
val v = n()


val isHello = try {
  "hello".toBoolean
} catch {
  case e: Exception => println(s"Some Exception occurred: $e")
  false
}
isHello


// Stepik
// еще пример partial function на Stepik
val divide10: PartialFunction[Int, Int] = {
  case 1 => 10
  case 2 => 5
  case 5 => 2
  case 10 => 1
}
// с помощью метода isDefinedAt определяем, для каких значений partial function
// divide10 определена, а для каких нет (т.е. был бы Match Error)
divide10.isDefinedAt(2) // true
divide10.isDefinedAt(3) // false

// часто partial functions используются с методами, например collect
// если результат есть - сложит в коллекцию
List.range(1,11)                    // List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
List.range(1,11).collect(divide10)  // List(10, 5, 2, 1)
