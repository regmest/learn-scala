package stepik

object Tasks {
    def main(args: Array[String]): Unit = {

      println(normalDistribution(1.0, 1.0, 1.0)) // 0.3989422804014327
      println(crispsWeight(90.0, 0.9, 0.1)) // 10.00000

    }

    def normalDistribution(mu: Double, sigma: Double, x: Double): Double = {
      1 / (sigma * math.sqrt(2 * math.Pi)) * math.exp(- math.pow(x - mu , 2) / 2 * math.pow(sigma, 2))
    } // println(normalDistribution(1.0, 1.0, 1.0)) // 0.3989422804014327

    // weight: BigDecimal  - вес имеющегося картофеля.
    // potatoWaterRatio: Double  - (0,1), доля воды в картофеле до того, как из него сделали чипсы.
    // crispsWaterRatio: Double - (0,1), доля воды в чипсах.
    // Найдите массу чипсов, которая получится.
    println(crispsWeight(90.0, 0.9, 0.1)) // 10.00000
    def crispsWeight(weight: BigDecimal, potatoWaterRatio: Double, crispsWaterRatio: Double): BigDecimal = {
      import scala.math.BigDecimal.RoundingMode.HALF_UP
      (weight * (1.0 - potatoWaterRatio) / (1.0 - crispsWaterRatio)).setScale(5, HALF_UP)
    } // println(crispsWeight(90.0, 0.9, 0.1)) // 10.00000

    // Посчитайте число единиц в битовой записи числа, считанного с клавиатуры, и выведите на экран.
    def countOnes1(): Unit = { // one way
      import scala.io.StdIn.readInt
      var cnt = 0
      for (i <- readInt().toBinaryString) {
        if (i == '1') cnt += 1 // '1' is Char, "1" is String
      }
      println(cnt)
    }

    def countOnes2(): Unit = { // another way, more scala-ble
      import scala.io.StdIn.readInt
      val cnt = readInt().toBinaryString.count(s => s == '1')
      println(cnt)
    }

}
