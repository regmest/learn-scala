package book

object Main extends App {

  val tom = new Person("Tom", 30)
  val kate = new Person("Kate", 30)

  // one function - apply - used with different params
  val child = Person.apply(tom, kate)
  println(child.name, child.age) // (Child of Tom and Kate,0)
  val bob = Person.apply("Bob")
  println(bob.name, bob.age) // (Bob,0)
  val jane = Person.apply(2)
  println(jane.name, jane.age) // (Jane Doe,2)

  // the word apply could be omitted (syntactic sugar)
  val john = Person("John")
  println(john.name, john.age) // (John,0)

}

class Person(val name: String, val age: Int)

object Person {

  // an alternative way of creating a person
  def apply(p1: Person, p2: Person): Person = {
    val name = s"Child of ${p1.name} and ${p2.name}"
    val age = 0
    new Person(name, age)
  }

  // we can define more apply methods as long as they have different signature
  def apply(name: String): Person = new Person(name, 0)
  def apply(age: Int): Person = new Person("Jane Doe", age)

}
