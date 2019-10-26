package com.exmpl.calculatorr.data.model.symb;

public final class Eight extends DecimalSymbol {

    private static final Eight instance = new Eight();

    private Eight() {
        super('8');
    }

    public static Eight getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Eight && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
