import scala.annotation.tailrec
import scala.io.StdIn
// Виды коллекций:
// ---------------
//    - immutable (List[+A], Stream[+A], Vector[+A], Set[+A], Map[K, +V]) - import scala.collection.immutable._
//    - mutable (Buffer[A], Set[A], Map[A, B], Builder[-E, +C] - import scala.collection.mutable._
// Их ^^ общие надтипы лежат в пакете import from scala.collection._ (Seq[+A], Set[+A], Map[K, +V], Iterator[+A])
//    - специализированные коллекции - Array[A], String


// Array (специализированная коллекция)
// -----
// - Очень эффективный низкоуровневый набор элементов
// - Фиксированного размера
// - Эффективен в том числе и потому, что у него есть специализированные версии для примитивных типов (Int, Long, Double, Boolean)

val ints = Array(1,2,3,5) // выделяется массив фиксированной заранее известной длины
ints(2)
ints(2) = 6
ints(2) // 6
// ints(4) // java.lang.ArrayIndexOutOfBoundsException

// String (специализированная коллекция)
// ------
// - тоже массив символов
// - но неизменяемый - любое изменение выделяет новую строку
val lang = "Scala"
val platform = "Stepik"
// Конкатенация - не самый эффективный способ, для каждого промежуточного действия выделяется новая строка
val course = lang + " " + platform
// Интерполяция гораздо эффективнее
val course1 = s"$lang $platform"
// Вытащить символ по индексу
val char: Char = course(3)


// Mutable
// -------
// - Состояние может меняться со временем
// - Эффективнее неизменяемых коллекций при большом количестве операций подряд
// - Копирование их не эффективно (чтобы скопировать изменяемую коллекцию, нужно выделить новое место
// для всех элементов и скопировать каждый в новую коллекцию)

// Buffer[A] - массив, в который можно добрасывать элементы с конца; "саморастущий массив"
// Set[A] - коллекция уникальных элементов
// Map[K, V] - ассоциативный массив "ключ-значение"
// Builder[E, Coll] - специальный промежуточный тип, накопитель для построения коллекции

import scala.collection.mutable

val strings = mutable.Buffer[String]()
strings += "hello" // мутация
strings += "scala"
strings.mkString(" ")


// Immutable
// ---------
// - Состояние неизменно
// - При изменении эффективное создание копий с переиспользованием пространства коллекций из которых создается новый элемент
// - Тк не изменяются, они Hashable, могут храниться в Set, быть ключами в Map

// List[A]    - Односвязный конечный список, легко добавить элемент в начало
// Stream[A]  - Ленивый односвязный список ("хвост" вычисляется лениво), лекго добавить элемент в начало,
// вомзножно бесконечный (тк из-за "ленивости" может не держать в памяти все элементы)
// Vector[A]  - Индексированный список, легко получить элемент по индексу, добавить элемент в начало или конец
// Set[A] - набор уникальных элементов
// Map[K, V] - ассоциативный массив "ключ-значение"

val initial = Vector[String]("stepik") // Vector(stepik)
val mid = "scala" +: "+" +: initial // добавляем элементы в начало; Vector(scala, +, stepik)
val str = mid :+ "=" :+ "love"      // добавляем элементы в конец;  Vector(scala, +, stepik, =, love)

str.mkString(" ") // String = scala + stepik = love

// Практика
// --------
val list = List(2,5,7,1,4)
list.sorted // List(1, 2, 4, 5, 7)

// Добавить элемент/коллекцию
0 :: List(1,2,3) == 0 +: List(1,2,3)  // true; добавить элемент в начало списка
// со списком можно работать и с `::`, и с `+:` - `+:` рабоатет для всех сиквенсов, более универсален
0 +: Array(1,2,3) // добавить элемент в начало коллекции; Array(0, 1, 2, 3)
Array(1,2,3) :+ 4 // добавить элемент в конец коллекции;  Array(1, 2, 3, 4)
Array(0) ++ Array(1,2,3) // соединить коллекции;          Array(0, 1, 2, 3)
List(0) ++ List(1,2,3) //                                 List(0, 1, 2, 3)


// Создаем коллекцию произвольного размера
List.fill(10)(2) // создаст коллекцию с десятью двойками List(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
List.fill(3,4)(2) // список с тремя списками по 4 двойки List(List(2, 2, 2, 2), List(2, 2, 2, 2), List(2, 2, 2, 2))
List.fill(3,4,5)(2) // список с треся списками по 4 списка по 5 двоек
import scala.util.Random
Random.nextInt(10) // value from 0 to 9
val randomList = List.fill(Random.nextInt(100))(Random.nextInt(1000)) // List(575, 529, 723, 1, 932, 289, 731, 104, 13, 294, 191 ...

// Классический, эффективный и хорошо подходяший для односвязных списков способ сортировки - сортировка слиянием
// Функция слияния 2х _отсортированных_ списков
@tailrec
def merge(as: List[Int], bs: List[Int], acc: List[Int] = Nil): List[Int] = {
  as match {
    case List() => acc.reverse ++ bs // если коллекция пустая, то перевернуть ее и добавить в конец список `bs` (тк мы начинаем добаление с маленьких, то они ушли в конец, а большие теперь в начале)
    case a +: tailA => // со списком можно работать и с `::`, и с `+:` - `+:` рабоатет для всех сиквенсов, более универсален
      bs match {
        case List() => acc.reverse ++ as
        case b +: tailB =>
          if (a < b)  // тк списки `as` и `bs` отсортироованы, то голова - наименьший элемент, можно добавить в итоговый список
            merge(tailA, bs, a :: acc)  // конструкция `a :: acc` добавляет `a` в начало списка `acc`; `bs` передаем как есть, потому что не знаем, вдруг в `as` еще будут элементы меньше, чем голова `bs`
          else
            merge(as, tailB, b :: acc)  // раз `b` меньше, добавляем в начало итогового списка его, а `as` отправляется дальше как есть
      }
  }
}
merge(List(2,5,6),List(1,4,9)) // List(1, 2, 4, 5, 6, 9) - два отсортированных списка слиты в новый отсортированный список

// Алгоритм сортировки слиянием
def mergeSort(as: List[Int]): List[Int] =
  as match {
    case Nil | (_ :: Nil) => as // если передана пустая коллекция или 1 элемент, сразу возвращаем эту коллекцию, тк пустая коллекция или один элемент всегда отсортированы
    case _ =>  // в остальных случаях делим коллецию пополам
      val (left, right) = as.splitAt(as.length / 2)
      val leftSorted = mergeSort(left) // здесь рекурсирно раздробим остаток списка и получим 1 элемент
      val rightSorted = mergeSort(right)
      merge(leftSorted, rightSorted) // сольем два сортированных списка
}
mergeSort(List(2,5,7,1,4)) // List(1, 2, 4, 5, 7)
mergeSort(randomList) == randomList.sorted // true; проверили для более длинном списке


// разделить список на два фильтром
val ints: List[Int] = List(0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0)
val (zeros, ones) = ints.partition(_ == 0) // A pair of, first, all elements that satisfy predicate p and, second, all elements that do not. Splits a collection in two.
zeros.mkString(",") // 0,0,0,0,0,0
ones.mkString(",")  // 1,1,1,1,1,1,1


// Выделить slice
val ints: List[Int] = List(0,1,2,3,4,5)
ints.slice(2,3) // List(2)
ints.slice(0,2) // List(0, 1)
