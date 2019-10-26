package com.exmpl.calculatorr.data.model.symb;

public final class Zero extends DecimalSymbol {

    private static final Zero instance = new Zero();

    private Zero() {
        super('0');
    }

    public static Zero getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Zero && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
