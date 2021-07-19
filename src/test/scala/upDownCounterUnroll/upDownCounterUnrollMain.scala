package upDownCounterUnroll

import chisel3.iotesters
import chisel3.stage.ChiselStage
import chisel3.iotesters.Driver
import fun_circuit.FunCircuit
import fun_circuit.FunCircuitRepl.args

class UpDownCounterUnrollMain extends App {
  Driver.execute(args, () => new UpDownCounterUnroll) {
    upDownCounter => new upDownCounterUnrollUnitTests(upDownCounter)
  }
  println((new ChiselStage).emitVerilog(new UpDownCounterUnroll))
}

