package upDownCounter

import chisel3._

class UpDownCounter extends Module {
  val io = IO(new Bundle {
    val inst = Input(SInt(1.W)) // 1-bit instruction.
    // 0 <-> count up
    // 1 <-> count down
    var value = Output(SInt(32.W))
  })

  var internalValue = RegInit(0.S(32.W))
  when ( Module.reset.asBool()) {
    internalValue := 0.S
  } otherwise  {
    when (io.inst.asBool()) {
      internalValue := internalValue + 1.S
    } otherwise {
      internalValue := internalValue - 1.S
    }
  }
  io.value := internalValue

}
