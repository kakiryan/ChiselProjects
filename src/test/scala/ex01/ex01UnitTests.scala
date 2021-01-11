package ex01

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class ex01UnitTests(ex01: Ex01) extends PeekPokeTester(ex01) {
  val random = new scala.util.Random
  for (t <- 0 until 2) {
    println("Clock cycle " + t)
    val value1 = random.nextInt(2)
    var result = 0
    if (result == 1) {
      result = 0
    } else {
      result = 1
    }
    poke(ex01.io.input, value1)
    step(1)
    expect(ex01.io.result, result)
  }
}

class Ex01Tester extends ChiselFlatSpec {
  behavior of "Ex01"
  backends foreach {backend =>
    it should s"simple negation with a mux $backend" in {
      Driver(() => new Ex01, backend)((ex01) => new ex01UnitTests(ex01)) should be (true)
    }
  }
}
