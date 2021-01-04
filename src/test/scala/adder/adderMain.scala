
package adder

import chisel3.stage.ChiselStage
import chisel3.iotesters.Driver

object AdderMain extends App {
  Driver.execute(args, () => new Adder) {
    adder => new AdderTests(adder)
  }
  println((new ChiselStage).emitVerilog(new Adder))
}

object AdderRepl extends App {
  Driver.executeFirrtlRepl(args, () => new Adder)
}