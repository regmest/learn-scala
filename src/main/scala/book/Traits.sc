

trait Animal {   // trait helps to identify an interface
  def sleep = "ZzZ"
  def eat(food: String): String     // requires to implement
  def move(x: Int, y: Int): String  // requires to implement
}

trait Nameable { // requires to implement name
  def name: String
}

class Cat extends Animal {
  override val sleep: String = "Sleepy cat!"  // modifying fully implemented function, so need to use "override" key-word
  def eat(food: String): String = s"The cat is eating $food" // "override" key-word could be omitted, bc eat was an abstract in Animal
  def move(x: Int, y: Int): String = s"The cat is moving to ($x,$y)"
}

class Dog(val name: String) extends Animal with Nameable { // extends .. with .. for 1+ traits
  def eat(food: String): String = s"$food!!!"
  def move(x: Int, y: Int): String = s"Move to ($x,$y)!"
}


val dog = new Dog("Spot")
val cat = new Cat

def feedTreat(animal: Animal): Unit = println(animal.eat("treat"))
feedTreat(dog)  // treat!!!
feedTreat(cat)  // The cat is eating treat

def welcome(nameable: Nameable): Unit = println(s"Welcome, ${nameable.name}!")
welcome(dog)    // Welcome, Spot!
// welcome(cat) // error! Cat is not Nameable


// sealed traits
// sealed trait can be extended only in the file where it was created
// сообщаем компилятору, что легитимно наследоваться только в этом файле
sealed trait Suit // масти карт

object Diamonds extends Suit
object Hearts extends Suit
object Spades extends Suit
object Clubs extends Suit
// но в другом файле отнаследоваться от Suit было бы нельзя



sealed trait Currency
object USD extends Currency
object EUR extends Currency
object GBP extends Currency

def exchangeRateUSD(currency: Currency): Double = currency match {
  case USD => 1
  case GBP => 0.744
  case EUR => 0.848
}
val curr = EUR
exchangeRateUSD(curr)



sealed trait TimeUnit {
  def getFromSeconds(tsSec: Long): Long
}
case object Nanos extends TimeUnit {
  def getFromSeconds(tsSec: Long): Long = tsSec * 1000000000
}
case object Micros extends TimeUnit {
  def getFromSeconds(tsSec: Long): Long = tsSec * 1000000
}
case object Millis extends TimeUnit {
  def getFromSeconds(tsSec: Long): Long = tsSec * 1000
}
case object Seconds extends TimeUnit {
  def getFromSeconds(tsSec: Long): Long = tsSec
}

val a = Nanos.getFromSeconds(1)


