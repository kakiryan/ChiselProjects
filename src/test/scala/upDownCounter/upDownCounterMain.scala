package upDownCounter

import chisel3.stage.ChiselStage
import chisel3.iotesters.Driver

class UpDownCounterMain extends App {
  Driver.execute(args, () => new UpDownCounter) {
    upDownCounter => new upDownCounterUnitTests(upDownCounter)
  }
  println((new ChiselStage).emitVerilog(new UpDownCounter))
}
