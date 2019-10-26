package com.exmpl.calculatorr.data.model.symb;

public final class Two extends DecimalSymbol {

    private static final Two instance = new Two();

    private Two() {
        super('2');
    }

    public static Two getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Two && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
