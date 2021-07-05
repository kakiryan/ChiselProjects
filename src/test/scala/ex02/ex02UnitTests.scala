package ex02

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import firrtl.FirrtlProtos.Firrtl.Expression.UIntLiteral
import chisel3._

class ex02UnitTests(ex02: Ex02) extends PeekPokeTester(ex02) {
  val random = new scala.util.Random
  println("Done building -- starting execution")
  for (t <- 0 until 1) {
    println("Clock cycle " + t)
    //val value1 = random.nextInt(2)
    poke(ex02.io.x_in, 12)
    poke(ex02.io.y_in, 5)
    println(ex02.io.x_in.toString())
    step(1)
    peek(ex02.io.x_out)
    expect(ex02.io.x_out, 5)
    expect(ex02.io.y_out, 12)
    expect(ex02.io.flag, 0)
  }
}

class Ex02Tester extends ChiselFlatSpec {
  behavior of "Ex02"
  backends foreach {backend =>
    it should s"simple if else $backend" in {
      Driver(() => new Ex02, backend)((ex02) => new ex02UnitTests(ex02)) should be (true)
    }
  }
}
