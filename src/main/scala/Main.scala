// Main is a singleton object; you can instantiate it only once.
// It is also an app; the JVM executes it as the first instruction when your program starts.
//object Main extends App {
//    println("Hello, World!")
//}

// also possible to write:
object Main {
    def main(args: Array[String]): Unit = {
        println("Hello, World!")
    }
}