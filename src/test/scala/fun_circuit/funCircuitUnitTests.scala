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

        val sum  = (select + 1) % 2

        poke(funCircuit.io.input1, input1)
        poke(funCircuit.io.input2, input2)
        step(1)
        poke(funCircuit.io.select, select)
        step(1)
        expect(funCircuit.io.sum, sum)
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