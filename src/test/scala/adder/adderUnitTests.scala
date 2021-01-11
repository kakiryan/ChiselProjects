package adder

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class AdderTests(adder: Adder) extends PeekPokeTester(adder) {
    val random = new scala.util.Random
    for (t <- 0 until 2) {
        println("Clock cycle " + t)
        val value1 = random.nextInt(2)
        val value2    = random.nextInt(2)
        val carryIn  = random.nextInt(2)
        val sum  = value1 ^ value2 ^ carryIn
        val carryOut = (value1 & value2) | (value1 & carryIn) | (value2 & carryIn)
        poke(adder.io.value1, value1)
        poke(adder.io.value2, value2)
        poke(adder.io.carryIn, carryIn)
        step(1)
        expect(adder.io.sum, sum)
        expect(adder.io.carryOut, carryOut)
    }
    }

    class AdderTester extends ChiselFlatSpec {
    behavior of "Adder"
    backends foreach {backend =>
        it should s"correctly add randomly generated numbers and show carry in $backend" in {
        Driver(() => new Adder, backend)((adder) => new AdderTests(adder)) should be (true)
        }
    }
    }