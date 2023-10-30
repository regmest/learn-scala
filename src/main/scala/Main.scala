import scala.io.StdIn.readLine
//import ru.tinkoff.Module
//import ru.tinkoff.Module.{oleg, kate}
//import ru.tinkoff.Module._
import ru.tinkoff.Module.{oleg, ivan => _, _}
import scala.math




// Main is a singleton object; you can instantiate it only once.
// It is also an app; the JVM executes it as the first instruction when your program starts.
//object Main extends App {
//    println("Hello, World!")
//}

// also possible to write with def main() and w/o "extends App":
object Main {


  def main(args: Array[String]): Unit = {

    //        val lucio = new ItalianRobot("Lucio")
    //        println(lucio.welcome("Regina"))


    val a = 2

    def b: Int = {
      println("init b")
      3
    }

    println(sum(a, a))
    println(sum(2,2))

    println(sum(3,3))
    println(sum(b, b))
  }

  def sum(a: Int, b: Int): Int = a + b



  def testOp(op: Option[Int]): Unit = {
    val r = for {
      l <- op
      l2 <- Option(1 * 100)
      l3 <- Option(s"${l2}")
    } yield l3

    r.foreach(println)
  }

  def test(): Unit = {
    val r = for {
      l <- List.range(0, 10)
      l2 <- List(l * 100)
      l3 <- List(s"${l + l2}")
    } yield s"test: $l3"

    r.foreach(println)
  }

  def test2(): Unit = {
    val r = List.range(0, 10).flatMap(
      l2 => List(l2 * 100).flatMap(
        l3 => List(s"${l2 + l3}").map(
          l4 => s"test: $l4"
        )
      )
    )
    r.foreach(println)
  }

  def test3(): Unit = {
    List.range(0, 10).map(
      l2 => l2 * 100
    ).map(
      l3 => s"$l3"
    ).map(
      l4 => s"test: $l4"
    ).foreach(println)
  }
}




