module Adder(
  input   clock,
  input   reset,
  input   io_value1,
  input   io_value2,
  input   io_carryIn,
  output  io_carryOut,
  output  io_sum
);
  wire  _T = io_value1 ^ io_value2; // @[Adder.scala 19:25]
  wire  _T_2 = io_value1 & io_value2; // @[Adder.scala 20:31]
  wire  _T_3 = io_value1 & io_carryIn; // @[Adder.scala 20:57]
  wire  _T_4 = _T_2 | _T_3; // @[Adder.scala 20:44]
  wire  _T_5 = io_value2 & io_carryIn; // @[Adder.scala 20:84]
  assign io_carryOut = _T_4 | _T_5; // @[Adder.scala 20:17]
  assign io_sum = _T ^ io_carryIn; // @[Adder.scala 19:12]
endmodule
