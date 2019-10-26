package com.exmpl.calculatorr.data.model.oprtr;

import com.exmpl.calculatorr.data.model.oprnd.Operand;

public interface Binary<T extends Operand, S extends Operand, R extends Operand> extends Functional {

    R invoke(T param1, S param2);
}
