package com.exmpl.calculatorr.data.model.oprtr;

import com.exmpl.calculatorr.data.model.oprnd.Operand;

public interface Unary<T extends Operand, R extends Operand> extends Functional {

    R invoke(T param);
}
