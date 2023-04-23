
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

val s = "foobarbaz" // fo obarb az -> fo brabo az
s.slice(0, 1) // f
s.slice(0, 2)
s.slice(2, 6 + 1).reverse
s.slice(6 + 1, s.length)
val boundaries = "2 6".split(' ').map(_.toInt)

