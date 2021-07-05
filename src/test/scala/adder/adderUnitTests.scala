package adder

import z3.scala.Z3Context

import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class AdderTests(adder: Adder) extends PeekPokeTester(adder) {
    val random = new scala.util.Random
    val z3 = new Z3Context()
    val solver = z3.mkSolver()
    for (t <- 0 until 1) {
        print(adder.getPorts)
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
        val sortb = z3.mkBVSort(32)
        val input1 = z3.mkConst(z3.mkStringSymbol(adder.io.value1.toString()), sortb)
        val input2 = z3.mkConst(z3.mkStringSymbol(adder.io.value2.toString()), sortb)
        val carryIn2 = z3.mkConst(z3.mkStringSymbol(adder.io.carryIn.toString()), sortb)
        val sum2 = z3.mkConst(z3.mkStringSymbol("sum"), sortb)
        val carryOut2 = z3.mkConst(z3.mkStringSymbol("carryOut"), sortb)
        val sum_cst = z3.mkEq(sum2, z3.mkBVXor(input1, z3.mkBVXor(input2, carryIn2)))
        val carryOut_cst = z3.mkEq(carryOut2, z3.mkBVOr(z3.mkBVAnd(input1, input2), z3.mkBVOr(z3.mkBVAnd(input2, carryIn2), z3.mkBVAnd(carryIn2, input1))))
        val property = z3.mkOr(sum_cst, carryOut_cst)

        // pushes the constraints to the Z3 context
        solver.assertCnstr(z3.mkAnd(sum_cst, z3.mkAnd(carryOut_cst, property)))

        val check_result = solver.check()

        check_result match {
            case (None) => println("Z3 failed.")
            case (Some(false)) => println("Unsat.")
            case (Some(true)) => println("SAT. Model:\n" + solver.getModel)
        }


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