
package fun_circuit

import chisel3._
import chisel3.util._
import chisel3.stage.ChiselStage

object FunCircuitMain extends App {
  iotesters.Driver.execute(args, () => new FunCircuit) {
    funCircuit => new FunCircuitTests(funCircuit)
  }
  println((new ChiselStage).emitVerilog(new FunCircuit))
}

object FunCircuitRepl extends App {
  iotesters.Driver.executeFirrtlRepl(args, () => new FunCircuit)
}