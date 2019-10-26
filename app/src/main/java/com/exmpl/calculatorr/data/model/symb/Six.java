package com.exmpl.calculatorr.data.model.symb;

public final class Six extends DecimalSymbol {

    private static final Six instance = new Six();

    private Six() {
        super('6');
    }

    public static Six getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Six && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
