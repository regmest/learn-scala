import scala.annotation.tailrec

val name = "Oleg"
name + "!!!" // concatenation
s"Welcome $name" // interpolation

val word = "Scala"
val pos = 0
word(pos) >= 'A' && word(pos) <= 'Z' // is in upper case?
for (i <- 65 to 90) println(i.toChar) // all upper case chars

"Scala".exists(_.isUpper) // has "Scala" an upper case char?

name.startsWith("Ol")
name.endsWith("eg")
name.contains("a")
name startsWith "Ol"
name endsWith "eg"
name contains "a"

name.matches("O.*g")

2.toString
2.0.toString
true.toString

2 + "string"
true + "string"

val s =
  """
    |abc
    |def
    |""".stripMargin
s.split("\n") // Array("", abc, def)

val x = BigInt(2).pow(10000)
  .toString() // 9950631168807583848837421626835850838234968318861924548520089498529438...
x.contains("777") // true
val regexpPattern = "(.)\\1".r  // повторение одного элемента 2 раза подряд
regexpPattern.findFirstIn(x)    // Some(99)
"(.)\\1\\1".r.findFirstIn(x)    // Some(111)
"(.)\\1\\1\\1".r.findFirstIn(x) // Some(3333)

val s = "123456789"
s.slice(2, 6)          // 3456
s.slice(2, 6).reverse  // 6543

def sumRange(from: Int, to: Int): Int =
  if (to < from) 0
  else from + sumRange(from + 1, to)

@tailrec
def sumRange1(from: Int, to: Int, acc: Long = 0): Long =
  if (to < from) acc
  else sumRange1(from + 1, to, acc + from)
sumRange1(1,10000000)

// 0, 1, 1, 2, 3, 5, 8 ..
@tailrec
def calcFib(endIndex: Int, currIndex: Int = 1, prevValue: Int = 0, currValue: Int = 1): Int = {
  if (endIndex == 1) 1
  else if (currIndex == endIndex) currValue
  else calcFib(endIndex, currIndex + 1, currValue, prevValue + currValue)
}

calcFib(1)
calcFib(2)
calcFib(3)
calcFib(4)
calcFib(5)