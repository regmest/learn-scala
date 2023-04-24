import org.scalameter.api._

import stepik.Tasks


class TEST extends Bench.OfflineReport {
  //  Tasks.fibs(10)


  performance of "fib" in {
    val idxs = Gen.single("idx")(1000)(org.scalameter.picklers.IntPickler)
    val arrays = for (sz <- idxs) yield sz

    using(arrays) in { x =>  Tasks.fibs0(x) }
  }
}
