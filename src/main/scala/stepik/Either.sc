
// - `Either[A, B]` - "одно из двух", "исключающее или", "либо"
// - имеет два подтипа
// 	 - `Left[A, B]` - левый вариант, содержащий значение А
// 	 - `Right[A, B]` - правый вариант, содержащий значание В


val numOrString1: Either[Double, String] = Left(2.12)     // val numOrString1: Either[Double,String] = Left(2.12)
val numOrString2: Either[Double, String] = Right("scala") // val numOrString2: Either[Double,String] = Right(scala)


// выяснить, какое из двух вариантов можно с помощью паттерн матчинга
def info(numOrString: Either[Double, String]): String = numOrString match {
  case Left(num) => s"number $num"
  case Right(str) => s"string $str"
}
info(numOrString1) // val res0: String = number 2.12
info(numOrString2) // val res1: String = string scala


// часто это используется при обработке ошибок
// похоже на Option, но в данном случае вместо None будет конкретное описание проблемы
def sqrt(x: Double): Either[String, Double] =
  if(x < 0) Left("negative number") // сообщение о проблеме
  else Right(Math.sqrt(x))
sqrt(4)  // Right(2.0)
sqrt(-1) // Left(negative number)

// работать с Right можно например так:
// подмена Right на дефолтное значение
sqrt(4).getOrElse(0)     // val res2: AnyVal = 2.0
sqrt(-1).getOrElse(0)    // val res3: AnyVal = 0  // в случ ошибки подменяем на дефолтное значение

// фильтрация предикатом filterOrElse
// выплевывает left, если right не прошло фильр; если изначально был Left - выплюнет его
sqrt(9).filterOrElse(_ > 2, "too small")  // Right(3.0)
sqrt(4).filterOrElse(_ > 2, "too small")  // Left(too small)
sqrt(-1).filterOrElse(_ > 2, "too small") // Left(negative number)

// преобразование Right
sqrt(4)                   // Either[String,Double] = Right(2)
sqrt(4).map(_.toString)   // Either[String,String] = Right(2)
sqrt(-1).map(_.toString)  // Either[String,String] = Left(negative number)

// если нужно применить функцию, тоже возвращающую Either
sqrt(4).flatMap(x => sqrt(x))    // Either[String,Double] = Right(1.4142135623730951)
sqrt(4).flatMap(x => sqrt(-1))   // Either[String,Double] = Left(negative number)
sqrt(-1).flatMap(x => sqrt(x))   // Either[String,Double] = Left(negative number)

// Метод flatMap действует до первого-Left
Right(1).flatMap(_ => Right(2)).flatMap(_ => Right(3)) // ok, Either[Nothing,Int] = Right(3)
//Right(1).flatMap(_ => Left(2)).flatMap(_ => Left(3))   // Either[Int,Nothing] = Left(2), не Left(3) как ожидалось


// Практика
// --------
// №1
// Правильной называется дробь, у которой модуль числителя меньше модуля знаменателя. Будем представлять дробь парой (числитель, знаменатель).
// Реализуйте операцию деления divide(p: (Int, Int))(q: (Int, Int)): Either[String,//  (Int, Int)], возвращающую результат деления p на q или текст ошибки.
// Проверьте корректность входных данных, если входные дроби неправильные, верните ошибку Left("Invalid input"). Если делитель нулевой, верните Left("Zero divisor"). Если в результате получилась неправильная дробь, ошибка Left("Improper result").
// Сократите результат до простой дроби. Воспользуйтесь алгоритмом Евклида, разобранным в модуле про кортежи, или методом BigInt.gcd .
def divide(p: (Int, Int))(q: (Int, Int)): Either[String, (Int, Int)] = {
  if (p._2 == 0 || q._2 == 0 || q._1 == 0) Left("Zero divisor") // проверка что делитель ненулевой
  else if (p._1.abs >= p._2.abs || q._1.abs >= q._2.abs) Left("Invalid input") // проверка что дроби правильные
  else if ((p._1 * q._2).abs >= (p._2 * q._1).abs) Left("Improper result") // проверка что результирующая дробь правильная
  else {
    val fraction = (p._1 * q._2, p._2 * q._1) // результирующая дробь
    val gcd = BigInt(fraction._1).gcd(fraction._2) // наибольший общий делитель
    Right(fraction._1 / gcd.toInt, fraction._2 / gcd.toInt) // сокращенная дробь
  }
}
// test cases
divide(1,10)(2,10) // Right((1,2))
divide(1, 0)(1, 2) // Left("Zero divisor")
divide(1, 2)(1, 0) // Left("Zero divisor")
divide(1, 2)(0, 2) // Left("Zero divisor")
divide(3, 4)(1, 10) // Left("Improper result")
divide(1, 2)(1, 2)  // Left("Improper result")
divide(2, 1)(1, 7) // Left("Invalid input")
divide(1, 2)(7, 1) // Left("Invalid input")
divide(1, 1)(2, 2) // Left("Invalid input")
divide(2, 1)(3, 1) // Left("Invalid input")
divide(1, 2)(2, 3)  // isRight
divide(-1, 2)(2, 3) // isRight
divide(1, -2)(2, 3) // isRight
divide(1, 2)(-2, 3) // isRight
divide(1, 2)(2, -3) // isRight
// №2
// Напишите функцию flatten(options: List[Option[Int]]): List[Int], возвращающую список из всех не-None элементов списка.
// Пользуйтесь частичными функциями.
def foo(options: List[Option[Int]]): List[Int] = {
  options.collect{case Some(x) => x}
}
def divide_(x: Int, y: Int): Option[Int] = if(y == 0) None else Some(x / y)
foo(List(divide_(10,2), divide_(10,0)))
// для фильтра листа от None можно было использовать метод .flatten
List(divide_(10,2), divide_(10,0)).flatten
