package com.exmpl.calculatorr.data.model.oprtr;

import com.exmpl.calculatorr.data.model.oprnd.Operand;

public final class DivideOperator
        extends Operator
        implements Binary<Operand, Operand, Operand>, Infix, LeftAssociative {

    public DivideOperator() {
        super(Operators.DIVISION_PRECEDENCE);
    }

    @Override
    public Operand invoke(Operand param1, Operand param2) {
        final double rslt = param1.getValue() / param2.getValue();
        return new Operand(rslt);
    }
}
