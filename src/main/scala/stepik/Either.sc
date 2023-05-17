
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

// работать с Right можно например так:
// подмена Right на дефолтное значение
sqrt(4).getOrElse(0)     // val res2: AnyVal = 2.0
sqrt(-1).getOrElse(0)    // val res3: AnyVal = 0  // в случ ошибки подменяем на дефолтное значение

// фильтрация предикатом
// выплевывает left, если right не прошло фильр; если изначально был Right - выплюнет его
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