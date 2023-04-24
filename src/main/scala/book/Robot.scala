package book

/**
 * companion object/class
 */

//object Main extends App {
//
//  val tom = new Robot("Tom") {
//    def welcome: String  = Vocabulary.sentenceA
//  }
//  val bob = new Robot("Bob") {
//    def welcome: String = Vocabulary.sentenceB
//  }
//
//  println(
//    Robot.mostTalkativeRobot(tom, bob).name
//  ) // Tom
//
//}

object Vocabulary {
  val sentenceA = "Hi there!"
  val sentenceB = "Welcome!"
  val sentenceC = "Hello :)"
}

// companion class;
abstract class Robot(val name: String) {
  def welcome: String
}

// companion object; keeps static functions for the companion class;
// static methods act more on a class rather than on a specific instance of that class
object Robot {
  def mostTalkativeRobot(r1: Robot, r2: Robot): Robot = {
    if (r1.welcome.length > r2.welcome.length) r1 else r2
  }
}


