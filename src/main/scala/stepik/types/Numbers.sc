import scala.math.BigDecimal.RoundingMode.HALF_UP

(Byte.MinValue, Byte.MaxValue)     // (-128,127); from -2^7 to 2^7
(Short.MinValue, Short.MaxValue)   // (-32768,32767); from -2^15 to 2^15
(Int.MinValue, Int.MaxValue)       // (-2147483648,2147483647); from -2^31 to 2^31
(Long.MinValue, Long.MaxValue)     // (-9223372036854775808,9223372036854775807); from -2^63 to 2^63
(Float.MinValue, Float.MaxValue)   // (-3.4028235E38,3.4028235E38)
(Double.MinValue, Double.MaxValue) // (-1.7976931348623157E308,1.7976931348623157E308)

123L // Long
123d // Double
123f // Float

123 + 23 // Int = 146

(1: Short) + (2: Short) // automatically converts to Int !
((1: Short) + (2: Short)).toShort

'A'.toInt // Int = 65, получаем численнное значение в кодировке
(65.toChar, 66.toChar, 67.toChar) // (Char, Char, Char) = (A,B,C)
'B' - 'A' // Int = 1

1000000000 * 1000000000 // Int = -1486618624, ошибка переполнения
1000000000L * 1000000000L // Long = 1000000000000000000

1000000000000000000L * 1000000000000000000L // Long = -5527149226598858752, ошибка переполнения
// BigInt помогает избежать проблем с переполнением;
// результат операции, между Int и BigInt = BigInt
val a = BigInt(1000000000000000000L) * 1000000000000000000L // scala.math.BigInt = 1000000000000000000000000000000000000
a.gcd(55) // наибольшый общий делитель scala.math.BigInt = 5
a.toString(16) // c097ce7bc90715b34b9f1000000000 это число в 16-ричной системе счисления
a.toString(2) // 11000000100101111100111001111011110010010000011100010101101100110100101110011111...
a.toString(36) // 1lq0c18ckzvyev7ttzd0msxs; максимум, после 36-ричной будем получать уже исходное 10-ричное значение
a.toString(37) // 1000000000000000000000000000000000000

"123123".toInt     // 123123
// "asda123".toInt // error !

val x = 2.0 // Double = 2.0
x + 2       // results in Double
// x + a    // error ! BigInt работает только с Int

val d = BigDecimal(10.0) // 10.0
d.setScale(5, HALF_UP)   // 10.00000

5.toBinaryString  // 101


'1' == "1" // false, bc '1' is a Char and "1" is a String