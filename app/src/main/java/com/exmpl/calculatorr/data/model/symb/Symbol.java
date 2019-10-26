package com.exmpl.calculatorr.data.model.symb;

public abstract class Symbol {

    private final char value;

    public Symbol(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Symbol) {
            final Symbol symb = (Symbol) obj;
            return this.getValue() == symb.getValue();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getValue();
    }
}
