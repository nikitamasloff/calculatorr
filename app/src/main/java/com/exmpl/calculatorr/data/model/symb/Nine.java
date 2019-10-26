package com.exmpl.calculatorr.data.model.symb;

public final class Nine extends DecimalSymbol {

    private static final Nine instance = new Nine();

    private Nine() {
        super('9');
    }

    public static Nine getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Nine && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
