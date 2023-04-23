package book

object Party {

  def main(args: Array[String]): Unit = {

    val party = new Party
    println(party.public_attendees)    // 0
    // party.private_attendees         // cannot be accessed


  }

  class Event {
    protected def estimateCosts(attendees: Int): Double = {  //  protected element is accessible from its class and subclasses
      if (attendees < 10) 50.00 else attendees * 12.34
    }

  }

  class Party extends Event {

    var public_attendees = 0  // default access level - public, accessible everywhere
    private var private_attendees = 0  // private access level, accessible nowhere, but inside its class

    var cost: Double = estimateCosts(private_attendees) // usage of protected func

    def register(guests: Int): Unit =
      private_attendees += guests

  }



}