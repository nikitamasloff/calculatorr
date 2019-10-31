package com.exmpl.calculatorr.calc;

import com.exmpl.calculatorr.excpt.BadExprException;
import com.exmpl.calculatorr.excpt.TooShortExprException;

public interface ExprBuilderr {

    void set(String src);

    boolean attach(String tkn);

    boolean detach();

    String build() throws TooShortExprException, BadExprException;
}
