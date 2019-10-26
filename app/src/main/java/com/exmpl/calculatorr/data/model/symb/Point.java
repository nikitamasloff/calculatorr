package com.exmpl.calculatorr.data.model.symb;

public final class Point extends DecimalSymbol {

    private static final Point instance = new Point();

    private Point() {
        super('.');
    }

    public static Point getInstance() {
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Point && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
