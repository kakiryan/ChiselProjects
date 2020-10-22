
package adder

import chisel3._
import chisel3.stage.ChiselStage

object AdderMain extends App {
  iotesters.Driver.execute(args, () => new Adder) {
    adder => new AdderTests(adder)
  }
  println((new ChiselStage).emitVerilog(new Adder))
}

object AdderRepl extends App {
  iotesters.Driver.executeFirrtlRepl(args, () => new Adder)
}