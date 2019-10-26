package com.exmpl.calculatorr.data.model.symb;

public final class Five extends DecimalSymbol {

    private static final Five instance = new Five();

    private Five() {
        super('5');
    }

    public static Five getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Five && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
