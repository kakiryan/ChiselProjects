package upDownCounter

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class upDownCounterUnitTests(counter: UpDownCounter) extends PeekPokeTester(counter) {
  println("Done building, starting execution")
//  for (t <- 0 until 5) {
//    println("Clock cycle" + t)
//    if (t < 2) {
//      poke(counter.io.inst, 1)
//      step(1)
//      expect(counter.io.value, t + 1)
//    } else {
//      poke(counter.io.inst, 0)
//      step(1)
//      if (t == 2) {
//        expect(counter.io.value, 1 )
//      } else if (t == 3) {
//        expect(counter.io.value, 0)
//      } else if (t == 4) {
//        expect(counter.io.value, -1)
//      }
//
//    }
//  }

//  for (t <- 0 until 1) {
//    println("Testing ")
//    reset(1)
//    expect(counter.io.value, 0)
//  }
//
  for (t <- 0 until 1) {
    poke(counter.io.inst, 1)
    step(1)
    expect(counter.io.value, t + 1)
  }



}

class UpDownCounterTester extends ChiselFlatSpec {
  behavior of "UpDownCounter"
  backends foreach {backend =>
    it should s"Count up or down depending on input $backend" in {
      Driver(() => new UpDownCounter, backend) ((counter) => new upDownCounterUnitTests(counter)) should be (true)
    }
  }
}
