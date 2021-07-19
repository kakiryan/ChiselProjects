package upDownCounterUnroll

import chisel3._

class UpDownCounterUnroll extends Module {
  val io = IO(new Bundle {
    val inst_1 = Input(SInt(1.W)) // 1-bit instruction.
    val inst_2 = Input(SInt(1.W))
    val inst_3 = Input(SInt(1.W))
    // 0 <-> count up
    // 1 <-> count down
    var value_1 = Output(SInt(32.W))
    var value_2 = Output(SInt(32.W))
    var value_3 = Output(SInt(32.W))
  })

  var internalValue = RegInit(0.S(32.W))
  withReset(reset.asBool()) {
    internalValue := 0.S
  }
  when(io.inst_1.asBool()) {
    internalValue := internalValue + 1.S
  } otherwise {
    internalValue := internalValue - 1.S
  }
  io.value_1 := internalValue

  when(io.inst_2.asBool()) {
    internalValue := internalValue + 1.S
  } otherwise {
    internalValue := internalValue - 1.S
  }
  io.value_2 := internalValue

  when(io.inst_3.asBool()) {
    internalValue := internalValue + 1.S
  } otherwise {
    internalValue := internalValue - 1.S
  }
  io.value_3 := internalValue

}
