package com.exmpl.calculatorr.data.model.symb;

public final class Infinity extends DecimalSymbol {

    private static final Infinity instance = new Infinity();

    private Infinity() {
        super('∞');
    }

    public static Infinity getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Infinity && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
