package com.exmpl.calculatorr.data.model.oprtr;

import com.exmpl.calculatorr.data.model.oprnd.Operand;

public final class MultiplyOperator
        extends Operator
        implements Binary<Operand, Operand, Operand>, Infix, LeftRightAssociative {

    public MultiplyOperator() {
        super(Operators.MULTIPLICATION_PRECEDENCE);
    }

    @Override
    public Operand invoke(Operand param1, Operand param2) {
        final double rslt = param1.getValue() * param2.getValue();
        return new Operand(rslt);
    }
}
