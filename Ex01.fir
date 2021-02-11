;buildInfoPackage: chisel3, version: 3.3.1, scalaVersion: 2.12.11, sbtVersion: 1.3.10
circuit Ex01 : 
  module Ex01 : 
    input clock : Clock
    input reset : UInt<1>
    output io : {flip input : UInt<1>, result : UInt<1>}
    
    node _T = bits(io.input, 0, 0) @[Ex01.scala 15:29]
    node _T_1 = mux(_T, UInt<1>("h00"), UInt<1>("h01")) @[Ex01.scala 15:19]
    io.result <= _T_1 @[Ex01.scala 15:13]
    
