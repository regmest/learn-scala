package stepik

import scala.annotation.tailrec
import org.scalameter.api._


object Tasks {

    def main(args: Array[String]): Unit = {

//      println(normalDistribution(1.0, 1.0, 1.0)) // 0.3989422804014327
//      println(crispsWeight(90.0, 0.9, 0.1)) // 10.00000
//      println(isCapital("Scala", 0))
//      println(revertStringPart())
//      println(checkSnakeCase())

//      val start = System.nanoTime
//      println(fibs(50))
//      println((System.nanoTime - start) / 1e9d)
//
//      val start_ = System.nanoTime
//      println(fibs0(50))
//      println((System.nanoTime - start_) / 1e9d)

//      printTimeAndResult(fibs(45))
//      printTimeAndResult(fibs0(45))
//      wrap(fibs(45))
//      wrap(fibs0(45))

      printCoprimeIntegers()

    }

    def normalDistribution(mu: Double, sigma: Double, x: Double): Double = {
      1 / (sigma * math.sqrt(2 * math.Pi)) * math.exp(- math.pow(x - mu , 2) / 2 * math.pow(sigma, 2))
    } // println(normalDistribution(1.0, 1.0, 1.0)) // 0.3989422804014327

    // weight: BigDecimal  - вес имеющегося картофеля.
    // potatoWaterRatio: Double  - (0,1), доля воды в картофеле до того, как из него сделали чипсы.
    // crispsWaterRatio: Double - (0,1), доля воды в чипсах.
    // Найдите массу чипсов, которая получится.
    def crispsWeight(weight: BigDecimal, potatoWaterRatio: Double, crispsWaterRatio: Double): BigDecimal = {
      import scala.math.BigDecimal.RoundingMode.HALF_UP
      (weight * (1.0 - potatoWaterRatio) / (1.0 - crispsWaterRatio)).setScale(5, HALF_UP)
    } // println(crispsWeight(90.0, 0.9, 0.1)) // 10.00000

    // Посчитайте число единиц в битовой записи числа, считанного с клавиатуры, и выведите на экран.
    def countOnes1(): Unit = { // one way
      import scala.io.StdIn.readInt
      var cnt = 0
      for (i <- readInt().toBinaryString) {
        if (i == '1') cnt += 1 // '1' is Char, "1" is String
      }
      println(cnt)
    }

    def countOnes2(): Unit = { // another way, more scala-ble
      import scala.io.StdIn.readInt
      val cnt = readInt().toBinaryString.count(s => s == '1')
      println(cnt)
    }

    // Релизуйте метод isCapital , который проверяет, является ли заглавной буква, стоящая на позиции pos: Int переданной строки word: String.
    // Все символы строки заданы в кодировке ASCII. Sample Input: Scala 0 Sample Output: true
    def isCapital(word: String, pos: Int): Boolean = {
     // word(pos).toInt >= 'A'.toInt && word(pos).toInt <= 'Z'.toInt
      word(pos) >= 'A' && word(pos) <= 'Z'
    }

  // Cчитайте из консоли два числа: startIndex и endIndex. Они расположены в первой строке и разделены одним пробелом. Далее считайте строку str.
  // Напечатайте в ответ входную строку, обратив порядок символов от startIndex до endIndex включительно. Sample Input: 2 6 foobarbaz  Sample Output: fobraboaz
    def revertStringPart(): String = {
      import scala.io.StdIn
      val Array(startIndex, endIndex) = StdIn.readLine().split(' ').map(_.toInt)
      val s = StdIn.readLine()
      s.slice(0, startIndex) + s.slice(startIndex, endIndex + 1).reverse + s.slice(endIndex + 1, s.length)
    }

   // Определить, является ли строка написанной в snake-case стиле:
   // 1. Строчные латинские буквы и символ подчёркивания
   // 2. Символ подчёркивания не должен стоять в начале и в конце строки
   // 3. Два символа подчёркивания не могут стоять рядом
    def checkSnakeCase(): Boolean = {
     import scala.io.StdIn.readLine
     val s = readLine()
     "[^a-z_]|^_.*|.*_$|__".r.findFirstIn(s).isEmpty
    }

    // Метод должен возвращать число Фибоначчи по его порядковому номеру.
    def fibs0(endIndex: Int): Int = { // мое решение (выигрывает по врмемени)
      @tailrec
      def calcFib(endIndex: Int, currIndex: Int = 1, prevValue: Int = 0, currValue: Int = 1): Int = {
        if (endIndex <= 0) 0
        else if (currIndex == endIndex) currValue
        else calcFib(endIndex, currIndex + 1, currValue, prevValue + currValue)
      }
      calcFib(endIndex)
    }
    def fibs(index: Int): Int = { // решение из комментариев
      if (index == 0) 0
      else if (index == 1 || index == 2) 1
      else fibs(index - 1) + fibs(index - 2)
    }

    // функции-таймеры
    def printTimeAndResult(f: => Any): Unit = {
      val start = System.nanoTime
      f
      println((System.nanoTime - start) / 1e9d)
    }
    def wrap[R](f: => R): R = {
      println("start")
      val r = f
      println("end")
      r
    }

    // Задание: напишите обобщенный метод middle возвращающий средний элемент из любого экземпляра Iterable[T]
    // Если элементов четное число, необходимо выбрать элемент с бОльшим порядковым номером.
    def middle[A](data: Iterable[A]): A = {
      data.splitAt(data.size / 2)._2.head
    }

    // Считайте с клавиатуры число n и выведите на экран все упорядоченные пары взаимно простых чисел
    // от 1 до n, не включая n, в лексикографическом порядке.
    def printCoprimeIntegers(): Unit = {
      import scala.io.StdIn
      val n: Int = StdIn.readInt()
      for (i <- 1 until n; j <- 1 until n if BigInt(i).gcd(j) == 1) println(s"$i $j")
    }

}
