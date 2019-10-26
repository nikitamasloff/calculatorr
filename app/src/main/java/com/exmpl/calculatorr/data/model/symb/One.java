package com.exmpl.calculatorr.data.model.symb;

public final class One extends DecimalSymbol {

    private static final One instance = new One();

    private One() {
        super('1');
    }

    public static One getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof One && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
