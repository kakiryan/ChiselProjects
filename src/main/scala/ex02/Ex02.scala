package ex02

import chisel3._

class Ex02 extends Module {
  val io = IO(new Bundle {
    val x_in = Input(UInt(32.W))
    val y_in = Input(UInt(32.W))
    var x_out = Output(UInt(32.W))
    var y_out = Output(UInt(32.W))
    var flag = Output(UInt(32.W))
  })

  when ( io.x_in > io.y_in ) {
    io.x_out :=  io.y_in
    io.y_out := io.x_in
    when (io.x_out > io.y_out) {
      io.flag := 1.U
    } otherwise {
      io.flag := 0.U
    }
  } otherwise  {
    io.x_out := io.x_in
    io.y_out := io.y_in
    io.flag := 0.U
  }
}
