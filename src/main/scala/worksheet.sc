import scala.math.BigDecimal.RoundingMode.HALF_UP

123L
123d
123f

123 + 23

(1: Short) + (2: Short) // automatically converts to Int !
((1: Short) + (2: Short)).toShort

'A'.toInt // получаем численнное значение в кодировке
(65.toChar, 66.toChar, 67.toChar)
'B' - 'A'

1000000000 * 1000000000 // ошибка переполнения
1000000000L * 1000000000L

1000000000000000000L * 1000000000000000000L // ошибка переполнения
// BigInt помогает избежать проблем с переполнением;
// результат операции, межжду Int и BigInt = BigInt
val a = BigInt(1000000000000000000L) * 1000000000000000000L
a.gcd(55) // наибольшый общий делитель
a.toString(16) // это число в 16-ричной системе счисления
a.toString(2)
a.toString(36) // максимум, после 36-ричной будем получать уже исходное 10-ричное значение
a.toString(37)

"123123".toInt
// "asda123".toInt // error !

val x = 2.0
x + 2 // results in Double
// x + a // error ! BigInt работает только с Int

val d = BigDecimal(10.0)
d.setScale(5, HALF_UP)
val c = BigInt(3)
for (i <- c.toString(2)) {
  println(s"i type is ${i.getClass}")

  println(i == '1')
  println(1.toChar)

}

5.toBinaryString
(5 >> 1).toBinaryString