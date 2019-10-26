package com.exmpl.calculatorr.data.model.symb;

public final class Three extends DecimalSymbol {

    private static final Three instance = new Three();

    private Three() {
        super('3');
    }

    public static Three getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Three && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
