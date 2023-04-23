package book

object VendingMachine {
  def main(args: Array[String]): Unit = {

    val myMachine = new VendingMachine

    // Initial state
    println(myMachine.chocolateBar) // 0
    println(myMachine.granolaBar) // 0

    // Add some bars to the machine
    myMachine.chocolateBar += 2
    println(myMachine.chocolateBar) // 2

    // Shopping
    println(myMachine.buy("chocolate bar", 1)) // Not enough money.
    println(myMachine.buy("granola bar", 1.0)) // The granola bar is not available now.
    println(myMachine.buy("chocolate bar", 2.0)) // Your request completed! Enjoy your chocolate bar.

    // Earnings
    println(myMachine.totalMoney) // 2.0

  }

  class VendingMachine {

    // TODO fix:
    // 1. everything is publicly accessible
    // 2. vars are bad practice: program is incapable of concurrent requests
    // 3. add the list of products machine can offer
    // 4. machine is not configurable

    var chocolateBar = 0
    val chocolateBarPrice = 1.50

    var granolaBar = 0
    val granolaBarPrice = 1.0

    var totalMoney = 0.0

    def buy(product: String, money: Double): String = {
      if (!isProductAvailable(product)) s"The $product is not available now."
      else if (!isMoneyEnough(product, money)) "Not enough money."
      else completeRequest(product, money)
    }

    def isProductAvailable(product: String): Boolean = {
      if (product == "chocolate bar") chocolateBar > 0
      else if (product == "granola bar") granolaBar > 0
      else false
    }

    def isMoneyEnough(product: String, money: Double): Boolean = {
      if (product == "chocolate bar") money >= chocolateBarPrice
      else if (product == "granola bar") money >= granolaBarPrice
      else false
    }

    def collectMoney(money: Double): Unit = totalMoney += money

    def releaseProduct(product: String): Unit = {
      if (product == "chocolate bar") chocolateBar -= 1
      else if (product == "granola bar") granolaBar -= 1
    }

    def completeRequest(product: String, money: Double): String = {
      collectMoney(money)
      releaseProduct(product)
      s"Your request completed! Enjoy your $product!"
    }

  }
}
