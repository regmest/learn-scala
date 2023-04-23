val x = 1 > 5
val y = "Oleg" contains "l"

x && y
x || y
!x

val a = "kate"
val b = "kate"
a == b
val c = a + b
val d = a + b
c == d
c eq d
a eq b // !!! true !!! ссылаются на один объект в памяти - строчку "kate"

