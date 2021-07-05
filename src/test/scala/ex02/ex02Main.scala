package ex02

import chisel3.stage.ChiselStage
import chisel3.iotesters.Driver
import ex01.Ex01Main.args
import ex02.{Ex02, ex02UnitTests}

object Ex02Main extends App {
  Driver.execute(args, () => new Ex02) {
    ex02 => new ex02UnitTests(ex02)
  }
  println((new ChiselStage).emitVerilog(new Ex02))
}
