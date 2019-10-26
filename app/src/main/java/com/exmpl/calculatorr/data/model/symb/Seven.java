package com.exmpl.calculatorr.data.model.symb;

public final class Seven extends DecimalSymbol {

    private static final Seven instance = new Seven();

    private Seven() {
        super('7');
    }

    public static Seven getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Seven && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
