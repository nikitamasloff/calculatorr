package com.exmpl.calculatorr.data.model.symb;

public final class Minus extends DecimalSymbol {

    private static final Minus instance = new Minus();

    private Minus() {
        super('-');
    }

    public static Minus getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Minus && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
