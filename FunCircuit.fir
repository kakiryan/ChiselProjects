;buildInfoPackage: chisel3, version: 3.3.1, scalaVersion: 2.12.11, sbtVersion: 1.3.10
circuit FunCircuit : 
  module FunCircuit : 
    input clock : Clock
    input reset : UInt<1>
    output io : {flip input1 : UInt<1>, flip input2 : UInt<1>, flip select : UInt<1>, sum : UInt<1>}
    
    reg register : UInt, clock with : (reset => (reset, UInt<1>("h00"))) @[FunCircuit.scala 17:27]
    node _T = bits(io.select, 0, 0) @[FunCircuit.scala 18:31]
    node _T_1 = mux(_T, io.input1, io.input2) @[FunCircuit.scala 18:20]
    register <= _T_1 @[FunCircuit.scala 18:14]
    node _T_2 = add(register, UInt<1>("h01")) @[FunCircuit.scala 19:24]
    node _T_3 = tail(_T_2, 1) @[FunCircuit.scala 19:24]
    io.sum <= _T_3 @[FunCircuit.scala 19:12]
    
