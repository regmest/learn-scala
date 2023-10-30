val x ="oleg@mail.ru".split("@")
//x[1]
//"oleg@mail.ru".split("@")


def n(): Int = {
  try {
    throw new Exception("BOOM!")
    42
  } finally {
    println("Printing this in the end in any case.")
  }
}
val v = n()
// Printing this in the end in any case.
// java.lang.Exception: BOOM!