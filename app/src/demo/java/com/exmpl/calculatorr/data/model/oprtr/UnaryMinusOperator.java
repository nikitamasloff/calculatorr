package com.exmpl.calculatorr.data.model.oprtr;

import com.exmpl.calculatorr.data.model.oprnd.Operand;

public final class UnaryMinusOperator
        extends Operator
        implements Unary<Operand, Operand>, Prefix {

    public UnaryMinusOperator() {
        super(Operators.UNARY_MINUS_PRECEDENCE);
    }

    @Override
    public Operand invoke(Operand param) {
        final double rslt = -(param.getValue());
        return new Operand(rslt);
    }
}
