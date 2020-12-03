package fun_circuit

import java.io.File

import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class FunCircuitTests(funCircuit: FunCircuit) extends PeekPokeTester(funCircuit) {
    val random = new scala.util.Random
    val input1 = 0
    val input2   = 1
    for (t <- 0 until 4) {

        var select  = random.nextInt(2)
        // have to give it an inital value?
        var selected = 0 
        if (select == 1) {
            selected = input2
        } else {
            selected = input1
        }
        val sum  = (selected + 1) % 2

        poke(funCircuit.io.input1, input1)
        poke(funCircuit.io.input2, input2)
        poke(funCircuit.io.select, select)
        step(1)
        expect(funCircuit.io.sum, selected)
    }
    }

    class FunCircuitTester extends ChiselFlatSpec {
    behavior of "FunCircuit"
    backends foreach {backend =>
        it should s"correct perform weird addition in $backend" in {
        Driver(() => new FunCircuit, backend)((funCircuit) => new FunCircuitTests(funCircuit)) should be (true)
        }
    }
    }