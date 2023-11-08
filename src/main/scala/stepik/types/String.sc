import scala.annotation.tailrec

val name = "Oleg"
name + "!!!" // concatenation
s"Welcome $name" // interpolation

val word = "Scala"
val pos = 0
word(pos) >= 'A' && word(pos) <= 'Z' // is in upper case?
for (i <- 65 to 90) println(i.toChar) // all upper case chars
// A B C D E F G H I J K L M N O P Q R S T U V W X Y Z

"Scala".exists(_.isUpper) // has "Scala" an upper case char?

name.startsWith("Ol")
name.endsWith("eg")
name.contains("a")
name startsWith "Ol"
name endsWith "eg"
name contains "a"

name.matches("O.*g") // true

2.toString    // String = 2
2.0.toString  // String = 2.0
true.toString // String = true

2 + "string"    // String = 2string
true + "string" // String = truestring

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

val schemaTable = "schema.table".split("\\.")
schemaTable(0)
schemaTable(1)

