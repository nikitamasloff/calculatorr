package com.exmpl.calculatorr.calc;

import com.exmpl.calculatorr.excpt.NaNException;

public interface Calculatorr {

    String calculate(String expr) throws NaNException;
}
