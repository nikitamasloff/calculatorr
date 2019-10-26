package com.exmpl.calculatorr.data.model.oprtr;

import com.exmpl.calculatorr.data.model.Token;

public abstract class Operator extends Token {

    private final int precedence;

    public Operator(int precedence) {
        this.precedence = precedence;
    }

    public int getPrecedence() {
        return precedence;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Operator) {
            final Operator oprtr = (Operator) obj;
            return this.getPrecedence() == oprtr.getPrecedence();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getPrecedence();
    }
}
