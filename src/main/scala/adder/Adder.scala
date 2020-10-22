package adder

import chisel3._

/** 
1 bit adder. Accepts two 1 bit inputs and a carry in
outputs a 1 bit sum and carry. Just getting started.
*/

class Adder extends Module {
    val io = IO(new Bundle {        
        val value1 = Input(UInt(1.W))
        val value2 = Input(UInt(1.W))
        val carryIn = Input(UInt(1.W))
        val carryOut = Output(UInt(1.W))
        val sum = Output(UInt(1.W))
    }) 

    io.sum := io.value1 ^ io.value2 ^ io.carryIn
    io.carryOut := (io.value1 & io.value2) | (io.value1 & io.carryIn) | (io.value2 & io.carryIn)

}