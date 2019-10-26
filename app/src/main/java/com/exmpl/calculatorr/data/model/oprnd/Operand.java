package com.exmpl.calculatorr.data.model.oprnd;

import com.exmpl.calculatorr.data.model.Token;

public class Operand extends Token {

    private final double value;

    public Operand(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (this.getClass() == obj.getClass()) {
            final Operand oprnd = (Operand) obj;
            return this.getValue() == oprnd.getValue();
        }
        return false;
    }

    @Override
    public int hashCode() {
        final long longBits = Double.doubleToLongBits(getValue());
        return (int) (longBits ^ (longBits >>> 32));
    }
}
