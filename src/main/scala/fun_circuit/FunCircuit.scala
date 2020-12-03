package fun_circuit

import chisel3._

/** 
Example with a mux, clock and weird adder.
*/

class FunCircuit extends Module {
    val io = IO(new Bundle {    
        val input1 = Input(UInt(1.W))    
        val input2 = Input(UInt(1.W))
        val select = Input(UInt(1.W))
        val sum = Output(UInt(1.W))
    }) 

    val register = RegInit(0.U)
    register := Mux(io.select.asBool, io.input1, io.input2)
    io.sum := register + 1.U
    // val carryOut = register & 1.U
}