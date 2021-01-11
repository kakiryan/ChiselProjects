package ex01

import chisel3.stage.ChiselStage
import chisel3.iotesters.Driver

object Ex01Main extends App {
  Driver.execute(args, () => new Ex01) {
    ex01 => new ex01UnitTests(ex01)
  }
  println((new ChiselStage).emitVerilog(new Ex01))
}

