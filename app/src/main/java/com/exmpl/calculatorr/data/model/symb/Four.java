package com.exmpl.calculatorr.data.model.symb;

public final class Four extends DecimalSymbol {

    private static final Four instance = new Four();

    private Four() {
        super('4');
    }

    public static Four getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Four && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
