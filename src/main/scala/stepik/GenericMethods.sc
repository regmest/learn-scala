def ifThenElse[A](cond: Boolean, t: => A, e: => A): A = if(cond) t else e
// конкретно указываем, на какой тип подменяем A
ifThenElse[String](1 > 2, "one", "two") // "two"
ifThenElse[Int](1 > 2, 1, 2) // 2
// компилятор сам выводит, что за тип имеется в виду под A
ifThenElse(1 > 2, 1, 2) // 2


def combineOn[A, B](comb: (B, B) => B)            // функция, работающая на одном типе
                   (f: A => B, g: B => A)         // 2 функции изменения типа
                   : (A, A) => A =
  (x, y) => g(comb(f(x), f(y)))                   // (x -> B, y -> B) -> A

val sumStrings = combineOn[String, Int](_ + _)(_.toInt, _.toString)
sumStrings("123", "32") // "155" // ("123".toInt + "32".toInt).toString
val sumInts = combineOn[Int, String](_ + _)(_.toString, _.toInt)
sumInts(123, 32) // 12332  // (123.toString + 32.toString).toInt


// обобщим метод calc42
def calc42M: (Int => Int) => Int = f => f(42_000_000)
def calc42M[A]: (Int => A) => A = f => f(42_000_000)
calc42M(_.toString) // "42000000"
// хотим посчитать сумму от 1 до 42_000_000
// напишем рекурсивную функцию в обобщенном виде
import scala.annotation.tailrec
def tailRec[A, B](iter_value: A => A,
                  iter_acc: (B, A) => B,
                  cond: A => Boolean)(start: A, init: B): B = {
  @tailrec
  def go(x: A, acc: B): B = {
    if(cond(x)) go(iter_value(x), iter_acc(acc, x)) // если условие выполняется, итерируем дальше
    else acc
  }
  go(start, init)
}

calc42M(n => tailRec[Int, Long](_ - 1, _ + _, _ >= 0)(n, 0))



def middle[A](data: Iterable[A]): A = {
  data.splitAt(data.size / 2)._2.head
}
require(middle("Scala") == 'a')
require(middle(Seq(1, 7, 5, 9)) == 5)

