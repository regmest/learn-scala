
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

def sqrtOrZeroNew(n: Int): Double = sqrt.orElse(zero)(n)
def sqrtOrValueNew(n: Int): Double = sqrt.orElse(value)(n)

sqrtOrZero(-5)
sqrtOrZeroNew(-5)
sqrtOrValue(-5)
sqrtOrValueNew(-5)


class MyException(msg: String) extends Exception(msg)
//throw new MyException("BOOM!")

def n(): Int = {
  try {
    throw new Exception("BOOM!")
    42
  } catch {
    case ex: Exception =>
      println(s"Ignoring exception $ex. Returning zero instead.")
      0
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