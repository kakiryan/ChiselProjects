package upDownCounter

import chisel3.iotesters
import chisel3.stage.ChiselStage
import chisel3.iotesters.Driver
import fun_circuit.FunCircuit
import fun_circuit.FunCircuitRepl.args

class UpDownCounterMain extends App {
  Driver.execute(args, () => new UpDownCounter) {
    upDownCounter => new upDownCounterUnitTests(upDownCounter)
  }
  println((new ChiselStage).emitVerilog(new UpDownCounter))
}

object UpDownCounterRepl extends App {
  iotesters.Driver.executeFirrtlRepl(args, () => new UpDownCounter)
}
