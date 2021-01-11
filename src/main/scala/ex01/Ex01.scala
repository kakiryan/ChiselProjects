package ex01

import chisel3._

/**
Simple example with a mux. Just negates the input.
 */

class Ex01 extends Module {
  val io = IO(new Bundle {
    val input = Input(UInt(1.W))
    val result = Output(UInt(1.W))
  })

  io.result := Mux(io.input.asBool, 0.U, 1.U)
}
