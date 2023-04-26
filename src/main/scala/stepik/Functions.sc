// синтаксис 
val addOne: Int => Int = x => x + 1
val plus: (Int, Int) => Int = (x, y) => x + y
// ИЛИ, но тут scala будет пытаться вывести тип результата сама 
val addOne = (x: Int) => x + 1
val plus = (x: Int, y: Int) => x + y
// ИЛИ заменять параметры на _, если он один или используются ровно по порядку 
val addOne: Int => Int = _ + 1  // так 
val plus = (_: Int) + (_: Int)  // или так 


// можно превратить метод в функцию с помощью ЭТА-конверсии 
def addOne(x: Int):Int = x + 1
def plus(x: Int, y: Int):Int = x + y
val addOneF = addOne _               // так 
val plusF: (Int, Int) => Int = plus  // или так 



// каррирование 
// представляем функцию многих параметров как последовательность функций от одного параметра, возвращающих функцию 
def plus: Int => (Int => Int) = x => y => x + y
plus(1)(2) // 3
val p: Int => Int = plus(1)
val p2: Int = p(2)
// можно превратить функцию многих параметров в каррированный вариант с помощью метода curried 
val plus3 = (x: Int, y: Int, z: Int) => x + y + z
val plus3C = plus3.curried
plus3C(1)(2)(3) // 6 


// композиция 
// создание функции из последовательного вызова других функций 
val plus1 = (_: Int) + 1
val mul3 = (_: Int) * 3
val plusThenMul = plus1 andThen mul3  // plus1, mul3 
val mulThenPlus = plus1 compose mul3  // mul3, plus1 
plusThenMul(2) // 9   
mulThenPlus(2) // 7
mul3(plus1(2)) // 9  
plus1(mul3(2)) // 7



// функция, которая приминмает другую функицю и запускает ее для значения 42
val calc42 = (f: Int => Int) => f(42)
val add1 = (_: Int) + 1
calc42(add1) // 43
calc42(_ + 1) // 43 // вместо add1 использовали lambda-функцию, описанную сразу на месте

// вычисляем сумму чисел от 1 до x
def sumTo(x: Int): Int = if (x == 0) 0 else x + sumTo(x - 1)
calc42(sumTo)
// а как передать sumTo (рекурсивную функцию) в качестве лямбда-функции?
// использовать Y-Combinator - он находит fixed-point
//calc42((x : Int) => if (x == 0) 0 else x + ) <-- чем заменить sumTo?
def fix(f: (=> Int => Int) => Int => Int): Int => Int = f(fix(f))

// рекурсивная лямбда-функция, описанная с помощью fix() и вызванная для значения 7
fix(rec => x => if (x == 0) 0 else x + rec(x - 1))(7)
// передали лямба-функцию альтернативную sumTo в calc42
calc42(fix(rec => x => if (x == 0) 0 else x + rec(x - 1)))


